package Bottom.ReportMaker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Bottom.DbBottom;
import Bottom.XMLBottom;
import org.w3c.dom.Element;

public class DbXML_CDMReport extends XMLBottom implements InterfaceCriteria {
    public DbXML_CDMReport(){

    }

    public Element reportCDM_xml(){
        LocalDateTime now = LocalDateTime.now();

        /*
         * 1 차 필터링 기준 적용
         * 보고서 작성 시간부터 5일 이내    now < TCA < now + 5 일
         * 최대 근접거리 5000 m 이내        MISS_DISTANCE < 5000
         */
        //String query_filter_first = String.format("select max(creation_date), message_id, satcat.orbital_period, miss_distance, collision_probability, tca, eventnum from cdm, satcat where '%s' < tca and tca < '%s' and miss_distance < 5000 group by eventnum",                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), now.plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String query_filter_first = String.format("select creation_date, message_id, satcat.orbital_period, miss_distance, collision_probability, tca, eventnum from cdm, satcat where cdm.sat1_international_designator = satcat.international_designator and miss_distance < 5000 order by miss_distance asc limit 20");

        return screening_toXML(query_filter_first);

    }

    public Element screening_toXML(String query_filter){
        initDB();
        Element cdm_list = null;
        try {

            ResultSet res = state.executeQuery(query_filter);
            cdm_list = document.createElement("CDM_LIST");

            System.out.println(query_filter);

            while(res.next()){
                String message_id = res.getString(2);
                Float period_val = res.getFloat(3);
                Float miss_distance_val = res.getFloat(4);
                Float probability_val = res.getFloat(5);
                String met_criteria_val = "NO";

                /*LEO 기준
                 * period < 1000 minute
                 * miss_distance < 1000 m
                 * probability > 0.00001
                 */
                System.out.println("collision probability is : "+ probability_val);
                System.out.println("criteria is: " + criteria_leo_probability);
                System.out.println(probability_val > criteria_leo_probability);
                if (((period_val < criteria_leo_period) && (miss_distance_val < criteria_leo_distance) && (probability_val > criteria_leo_probability)) ||
                        ((period_val >= criteria_leo_period) && (miss_distance_val < criteria_geo_distance))) {
                    met_criteria_val = "YES";
                    /*
                    1) 전파대상 CDM은 이메일 전파를 위해 XML 형식으로 저장
                    2) 전파대상 CDM은 우주동향 작성을 위해 별도 테이블 관리
                     */
                    Db_Json2XML.cdm_2down.add(message_id);
                    //new Db_Json2XML().json2xml(message_id);
                }

                Element cdm = document.createElement("cdm");
                Element creation_date = document.createElement("creation_date");
                Element period = document.createElement("PERIOD");
                Element miss_distance = document.createElement("MISS_DISTANCE");
                Element probability = document.createElement("PROBABILITY");
                Element tca = document.createElement("TCA");
                Element eventnum = document.createElement("EVENTNUM");
                Element met_criteria = document.createElement("MET_CRITERIA");

                creation_date.setTextContent(res.getString(1));
                period.setTextContent(res.getString(3));
                miss_distance.setTextContent(res.getString(4));
                probability.setTextContent(res.getString(5));
                tca.setTextContent(res.getString(6));
                eventnum.setTextContent(res.getString(7));
                met_criteria.setTextContent(met_criteria_val);

                cdm.appendChild(period);
                cdm.appendChild(miss_distance);
                cdm.appendChild(probability);
                cdm.appendChild(tca);
                cdm.appendChild(eventnum);
                cdm.appendChild(met_criteria);

                cdm_list.appendChild(cdm);
            }

            //XMLBottom.newElement(cdm_list);
            //XMLBottom.createXML("C:\\Users\\User\\IdeaProjects\\testXML.xml");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConn(conn);
        return cdm_list;
    }
}
