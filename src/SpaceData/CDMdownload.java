package SpaceData;

import Bottom.SpaceData.DbInsertCDM;
import Bottom.ReadConfig;
import Bottom.SpaceData.WebSPquery;
import Bottom.SpaceData.InterfaceSP;

import java.io.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDMdownload implements ActionListener, InterfaceSP {
    /*
        CDM 다운로드 클래스
        1. 마지막 Creation_date 날짜 불러옴
            ?? 없으면?
        2. Space-track.org CDM 쿼리 생성
        3. Space-track.org 에 쿼리 전송 후 CDM 다운로드 (CDMman.WebSPquery) -> String?
            ?? 오류 처리
        4. 다운로드 받은 CDM DB 입력
            ?? 오류 처리 ( 날짜 포맷변경 등 )
        5. Db에서 이벤트 번호 개수
        6. Panel 업데이트
     */


    @Override
    public void actionPerformed(ActionEvent e){
        String newCDM;
        HashMap<String, String> conf = new ReadConfig().read();
        WebSPquery sp = new WebSPquery(conf);

        String lastCDMcreationdate = "2020-10-10 10:00:00";

        String query = queryBuilder(lastCDMcreationdate);
        //newCDM = sp.spquery(query);
        newCDM = testCDM(conf.get("testCDM"));
        System.out.println(newCDM);
        new DbInsertCDM().insertCDM(newCDM, conf);


    }

    public String queryBuilder(String reftime){
        String url = InterfaceSP.baseSPurl + String.format(InterfaceSP.queryCDM, reftime);
        url = url.replace(" ","%20");
        url = url.replace(">", "%3E");
        url = url.replace("=","%3D");
        return url;
    }

    public String testCDM(String loc) {
        String outdata = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(loc));
            outdata = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outdata;

    }


    public static void main(String args[]) throws UnsupportedEncodingException {

    }
}
