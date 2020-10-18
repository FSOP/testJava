package Bottom.ReportMaker;

import Bottom.XMLBottom;
import org.w3c.dom.Element;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Db_Json2XML extends XMLBottom {
    static public ArrayList<String> cdm_2down= new ArrayList<>();
    String mid;

    public void json2xml(String message_id){
        initXml();
        initDB();
        mid = message_id;

        Element message = document.createElement("message");
        Element cdm = document.createElement("cdm");
        message.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        message.setAttribute("xsi:noNameSpaceSchemaLocation","http://sanaregistry.org/r/ndmxml/ndmxml-1.0-master.xsd");
        cdm.setAttribute("id", "CCSDS_CDM_VERS");
        cdm.setAttribute("version", "1.0");

        cdm.appendChild(xml_header());
        cdm.appendChild(xml_body());

        message.appendChild(cdm);

        closeConn(conn);

        createXML(message, "C:\\Users\\User\\IdeaProjects\\"+message_id+".xml");
    }

    public Element xml_header(){
        Element header= document.createElement("header");

        Element comment_cdm_id = document.createElement("COMMENT");
        Element creation_date = document.createElement("CREATION_DATE");
        Element originator = document.createElement("ORIGINATOR");
        Element message_for = document.createElement("MESSAGE_FOR");
        Element message_id = document.createElement("MESSAGE_ID");

        try {
            //initDB();
            ResultSet res= state.executeQuery("select CDM_ID,CREATION_DATE,ORIGINATOR,MESSAGE_FOR,MESSAGE_ID from cdm where message_id = \"" + mid +"\"");

            comment_cdm_id.setTextContent("CDM_ID:"+res.getString(1));
            creation_date.setTextContent(res.getString(2));
            originator.setTextContent(res.getString(3));
            message_for.setTextContent(res.getString(4));
            message_id.setTextContent(res.getString(5));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //closeConn(conn);
        }


        header.appendChild(comment_cdm_id);
        header.appendChild(creation_date);
        header.appendChild(originator);
        header.appendChild(message_for);
        header.appendChild(message_id);

        return header;
    }

    public Element xml_body(){
        Element body = document.createElement("body");

        body.appendChild(relative_metadata());
        body.appendChild(segment("SAT1"));
        body.appendChild(segment("SAT2"));

        return body;
    }

    public Element relative_metadata(){
        Element relative_metadata = document.createElement("relativeMetadata");
        Element relative_state_vector = document.createElement("relativeStateVector");


        Element comment_screening_option = document.createElement("COMMENT");
        Element tca = document.createElement("TCA");
        Element miss_distance  = document.createElement("MISS_DISTANCE");
        Element relative_speed  = document.createElement("RELATIVE_SPEED");
        Element relative_position_r  = document.createElement("RELATIVE_POSITION_R");
        Element relative_position_t  = document.createElement("RELATIVE_POSITION_T");
        Element relative_position_n  = document.createElement("RELATIVE_POSITION_N");
        Element relative_velocity_r  = document.createElement("RELATIVE_VELOCITY_R");
        Element relative_velocity_t  = document.createElement("RELATIVE_VELOCITY_T");
        Element relative_velocity_n  = document.createElement("RELATIVE_VELOCITY_N");
        Element collision_probability = document.createElement("COLLISION_PROBABILITY");
        Element collision_probability_method = document.createElement("COLLISION_PROBABILITY_METHOD");


        miss_distance.setAttribute("units","m");
        relative_speed.setAttribute("units","m/s");
        relative_position_r.setAttribute("units","m");
        relative_position_t.setAttribute("units","m");
        relative_position_n.setAttribute("units","m");
        relative_velocity_r.setAttribute("units","m/s");
        relative_velocity_t.setAttribute("units","m/s");
        relative_velocity_n.setAttribute("units","m/s");

        try {
            //initDB();
            String query = "select COMMENT_SCREENING_OPTION,TCA,MISS_DISTANCE,RELATIVE_SPEED,RELATIVE_POSITION_R,RELATIVE_POSITION_T,RELATIVE_POSITION_N,RELATIVE_VELOCITY_R,RELATIVE_VELOCITY_T,RELATIVE_VELOCITY_N,COLLISION_PROBABILITY,COLLISION_PROBABILITY_METHOD from cdm where message_id = \"" + mid +"\"";
            ResultSet res= state.executeQuery(query);

            comment_screening_option.setTextContent(res.getString(1));
            tca.setTextContent(res.getString(2));
            miss_distance .setTextContent(res.getString(3));
            relative_speed .setTextContent(res.getString(4));
            relative_position_r .setTextContent(res.getString(5));
            relative_position_t .setTextContent(res.getString(6));
            relative_position_n .setTextContent(res.getString(7));
            relative_velocity_r .setTextContent(res.getString(8));
            relative_velocity_t .setTextContent(res.getString(9));
            relative_velocity_n .setTextContent(res.getString(10));
            collision_probability.setTextContent(res.getString(11));
            collision_probability_method.setTextContent(res.getString(12));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //closeConn(conn);
        }

        relative_metadata.appendChild(comment_screening_option);
        relative_metadata.appendChild(tca);
        relative_metadata.appendChild(miss_distance );
        relative_metadata.appendChild(relative_speed );

        relative_state_vector.appendChild(relative_position_r );
        relative_state_vector.appendChild(relative_position_t );
        relative_state_vector.appendChild(relative_position_n );
        relative_state_vector.appendChild(relative_velocity_r );
        relative_state_vector.appendChild(relative_velocity_t );
        relative_state_vector.appendChild(relative_velocity_n );

        relative_metadata.appendChild(relative_state_vector);
        relative_metadata.appendChild(collision_probability);
        relative_metadata.appendChild(collision_probability_method);

        return relative_metadata;
    }

    public Element segment(String sat){
        Element segment = document.createElement("segment");
        Element data = document.createElement("data");

        Element odparameters = document.createElement("odParameters");
        Element comment_cov_scale = document.createElement("COMMENT");
        Element comment_exclusion_volume = document.createElement("COMMENT");
        Element time_lastob_start = document.createElement("TIME_LASTOB_START");
        Element time_lastob_end = document.createElement("TIME_LASTOB_END");
        Element recommended_od_span  = document.createElement("RECOMMENDED_OD_SPAN");
        Element actual_od_span  = document.createElement("ACTUAL_OD_SPAN");
        Element obs_available = document.createElement("OBS_AVAILABLE");
        Element obs_used = document.createElement("OBS_USED");
        Element residuals_accepted  = document.createElement("RESIDUALS_ACCEPTED");
        Element weighted_rms = document.createElement("WEIGHTED_RMS");

        Element additionalparameters = document.createElement("additionalParameters");
        Element comment_apogee = document.createElement("COMMENT");
        Element comment_perigee = document.createElement("COMMENT");
        Element comment_inclination = document.createElement("COMMENT");
        Element area_pc  = document.createElement("AREA_PC");
        Element cd_area_over_mass  = document.createElement("CD_AREA_OVER_MASS");
        Element cr_area_over_mass  = document.createElement("CR_AREA_OVER_MASS");
        Element thrust_acceleration  = document.createElement("THRUST_ACCELERATION");
        Element sedr  = document.createElement("SEDR");

        Element statevector = document.createElement("stateVector");
        Element x  = document.createElement("X");
        Element y  = document.createElement("Y");
        Element z  = document.createElement("Z");
        Element x_dot  = document.createElement("X_DOT");
        Element y_dot  = document.createElement("Y_DOT");
        Element z_dot  = document.createElement("Z_DOT");

        Element covariancematrix = document.createElement("covarianceMatrix");
        Element comment_dcp_density = document.createElement("COMMENT");
        Element comment_sensitivity_pos = document.createElement("COMMENT");
        Element comment_sensitivity_vel = document.createElement("COMMENT");
        Element cr_r  = document.createElement("CR_R");
        Element ct_r  = document.createElement("CT_R");
        Element ct_t  = document.createElement("CT_T");
        Element cn_r  = document.createElement("CN_R");
        Element cn_t  = document.createElement("CN_T");
        Element cn_n  = document.createElement("CN_N");
        Element crdot_r  = document.createElement("CRDOT_R");
        Element crdot_t  = document.createElement("CRDOT_T");
        Element crdot_n  = document.createElement("CRDOT_N");
        Element crdot_rdot  = document.createElement("CRDOT_RDOT");
        Element ctdot_r  = document.createElement("CTDOT_R");
        Element ctdot_t  = document.createElement("CTDOT_T");
        Element ctdot_n  = document.createElement("CTDOT_N");
        Element ctdot_rdot  = document.createElement("CTDOT_RDOT");
        Element ctdot_tdot  = document.createElement("CTDOT_TDOT");
        Element cndot_r  = document.createElement("CNDOT_R");
        Element cndot_t  = document.createElement("CNDOT_T");
        Element cndot_n  = document.createElement("CNDOT_N");
        Element cndot_rdot  = document.createElement("CNDOT_RDOT");
        Element cndot_tdot  = document.createElement("CNDOT_TDOT");
        Element cndot_ndot  = document.createElement("CNDOT_NDOT");
        Element cdrg_r  = document.createElement("CDRG_R");
        Element cdrg_t  = document.createElement("CDRG_T");
        Element cdrg_n  = document.createElement("CDRG_N");
        Element cdrg_rdot  = document.createElement("CDRG_RDOT");
        Element cdrg_tdot  = document.createElement("CDRG_TDOT");
        Element cdrg_ndot  = document.createElement("CDRG_NDOT");
        Element cdrg_drg  = document.createElement("CDRG_DRG");
        Element csrp_r  = document.createElement("CSRP_R");
        Element csrp_t  = document.createElement("CSRP_T");
        Element csrp_n  = document.createElement("CSRP_N");
        Element csrp_rdot  = document.createElement("CSRP_RDOT");
        Element csrp_tdot  = document.createElement("CSRP_TDOT");
        Element csrp_ndot  = document.createElement("CSRP_NDOT");
        Element csrp_drg  = document.createElement("CSRP_DRG");
        Element csrp_srp  = document.createElement("CSRP_SRP");
        recommended_od_span.setAttribute("units","d");
        actual_od_span.setAttribute("units","d");


        residuals_accepted.setAttribute("units","%");
        area_pc.setAttribute("units","m**2");
        cd_area_over_mass.setAttribute("units","m**2/kg");
        cr_area_over_mass.setAttribute("units","m**2/kg");
        thrust_acceleration.setAttribute("units","m/s**2");
        sedr.setAttribute("units","W/kg");

        x.setAttribute("units","km");
        y.setAttribute("units","km");
        z.setAttribute("units","km");
        x_dot.setAttribute("units","km/s");
        y_dot.setAttribute("units","km/s");
        z_dot.setAttribute("units","km/s");

        cr_r.setAttribute("units","m**2");
        ct_r.setAttribute("units","m**2");
        ct_t.setAttribute("units","m**2");
        cn_r.setAttribute("units","m**2");
        cn_t.setAttribute("units","m**2");
        cn_n.setAttribute("units","m**2");
        crdot_r.setAttribute("units","m**2/s");
        crdot_t.setAttribute("units","m**2/s");
        crdot_n.setAttribute("units","m**2/s");
        crdot_rdot.setAttribute("units","m**2/s**2");
        ctdot_r.setAttribute("units","m**2/s");
        ctdot_t.setAttribute("units","m**2/s");
        ctdot_n.setAttribute("units","m**2/s");
        ctdot_rdot.setAttribute("units","m**2/s**2");
        ctdot_tdot.setAttribute("units","m**2/s**2");
        cndot_r.setAttribute("units","m**2/s");
        cndot_t.setAttribute("units","m**2/s");
        cndot_n.setAttribute("units","m**2/s");
        cndot_rdot.setAttribute("units","m**2/s**2");
        cndot_tdot.setAttribute("units","m**2/s**2");
        cndot_ndot.setAttribute("units","m**2/s**2");
        cdrg_r.setAttribute("units","m**3/kg");
        cdrg_t.setAttribute("units","m**3/kg");
        cdrg_n.setAttribute("units","m**3/kg");
        cdrg_rdot.setAttribute("units","m**3/(kg*s)");
        cdrg_tdot.setAttribute("units","m**3/(kg*s)");
        cdrg_ndot.setAttribute("units","m**3/(kg*s)");
        cdrg_drg.setAttribute("units","m**4/kg**2");
        csrp_r.setAttribute("units","m**3/kg");
        csrp_t.setAttribute("units","m**3/kg");
        csrp_n.setAttribute("units","m**3/kg");
        csrp_rdot.setAttribute("units","m**3/(kg*s)");
        csrp_tdot.setAttribute("units","m**3/(kg*s)");
        csrp_ndot.setAttribute("units","m**3/(kg*s)");
        csrp_drg.setAttribute("units","m**4/kg**2");
        csrp_srp.setAttribute("units","m**4/kg**2");

        try {
            //initDB();
            String query="";

            if( sat.equals("SAT1") ){
                query = "select SAT1_COMMENT_COVARIANCE_SCALE_FACTOR,SAT1_COMMENT_EXCLUSION_VOLUME_RADIUS,SAT1_TIME_LASTOB_START,SAT1_TIME_LASTOB_END,SAT1_RECOMMENDED_OD_SPAN,SAT1_ACTUAL_OD_SPAN,SAT1_OBS_AVAILABLE,SAT1_OBS_USED,SAT1_RESIDUALS_ACCEPTED,SAT1_WEIGHTED_RMS,SAT1_COMMENT_APOGEE,SAT1_COMMENT_PERIGEE,SAT1_COMMENT_INCLINATION,SAT1_AREA_PC,SAT1_CD_AREA_OVER_MASS,SAT1_CR_AREA_OVER_MASS,SAT1_THRUST_ACCELERATION,SAT1_SEDR,SAT1_X,SAT1_Y,SAT1_Z,SAT1_X_DOT,SAT1_Y_DOT,SAT1_Z_DOT,SAT1_COMMENT_DCP_DENSITY_FORECAST_UNCERTAINTY,SAT1_COMMENT_DCP_SENSITIVITY_VECTOR_POSITION,SAT1_COMMENT_DCP_SENSITIVITY_VECTOR_VELOCITY,SAT1_CR_R,SAT1_CT_R,SAT1_CT_T,SAT1_CN_R,SAT1_CN_T,SAT1_CN_N,SAT1_CRDOT_R,SAT1_CRDOT_T,SAT1_CRDOT_N,SAT1_CRDOT_RDOT,SAT1_CTDOT_R,SAT1_CTDOT_T,SAT1_CTDOT_N,SAT1_CTDOT_RDOT,SAT1_CTDOT_TDOT,SAT1_CNDOT_R,SAT1_CNDOT_T,SAT1_CNDOT_N,SAT1_CNDOT_RDOT,SAT1_CNDOT_TDOT,SAT1_CNDOT_NDOT,SAT1_CDRG_R,SAT1_CDRG_T,SAT1_CDRG_N,SAT1_CDRG_RDOT,SAT1_CDRG_TDOT,SAT1_CDRG_NDOT,SAT1_CDRG_DRG,SAT1_CSRP_R,SAT1_CSRP_T,SAT1_CSRP_N,SAT1_CSRP_RDOT,SAT1_CSRP_TDOT,SAT1_CSRP_NDOT,SAT1_CSRP_DRG,SAT1_CSRP_SRP from cdm where message_id = \"" + mid +"\"";
            } else if (sat.equals("SAT2")){
                query = "select SAT2_COMMENT_COVARIANCE_SCALE_FACTOR,SAT2_COMMENT_EXCLUSION_VOLUME_RADIUS,SAT2_TIME_LASTOB_START,SAT2_TIME_LASTOB_END,SAT2_RECOMMENDED_OD_SPAN,SAT2_ACTUAL_OD_SPAN,SAT2_OBS_AVAILABLE,SAT2_OBS_USED,SAT2_RESIDUALS_ACCEPTED,SAT2_WEIGHTED_RMS,SAT2_COMMENT_APOGEE,SAT2_COMMENT_PERIGEE,SAT2_COMMENT_INCLINATION,SAT2_AREA_PC,SAT2_CD_AREA_OVER_MASS,SAT2_CR_AREA_OVER_MASS,SAT2_THRUST_ACCELERATION,SAT2_SEDR,SAT2_X,SAT2_Y,SAT2_Z,SAT2_X_DOT,SAT2_Y_DOT,SAT2_Z_DOT,SAT2_COMMENT_DCP_DENSITY_FORECAST_UNCERTAINTY,SAT2_COMMENT_DCP_SENSITIVITY_VECTOR_POSITION,SAT2_COMMENT_DCP_SENSITIVITY_VECTOR_VELOCITY,SAT2_CR_R,SAT2_CT_R,SAT2_CT_T,SAT2_CN_R,SAT2_CN_T,SAT2_CN_N,SAT2_CRDOT_R,SAT2_CRDOT_T,SAT2_CRDOT_N,SAT2_CRDOT_RDOT,SAT2_CTDOT_R,SAT2_CTDOT_T,SAT2_CTDOT_N,SAT2_CTDOT_RDOT,SAT2_CTDOT_TDOT,SAT2_CNDOT_R,SAT2_CNDOT_T,SAT2_CNDOT_N,SAT2_CNDOT_RDOT,SAT2_CNDOT_TDOT,SAT2_CNDOT_NDOT,SAT2_CDRG_R,SAT2_CDRG_T,SAT2_CDRG_N,SAT2_CDRG_RDOT,SAT2_CDRG_TDOT,SAT2_CDRG_NDOT,SAT2_CDRG_DRG,SAT2_CSRP_R,SAT2_CSRP_T,SAT2_CSRP_N,SAT2_CSRP_RDOT,SAT2_CSRP_TDOT,SAT2_CSRP_NDOT,SAT2_CSRP_DRG,SAT2_CSRP_SRP from cdm where message_id = \"" + mid +"\"";
            }

            ResultSet res= state.executeQuery(query);

            comment_cov_scale.setTextContent(res.getString(1));
            comment_exclusion_volume.setTextContent(res.getString(2));
            time_lastob_start.setTextContent(res.getString(3));
            time_lastob_end.setTextContent(res.getString(4));
            recommended_od_span.setTextContent(res.getString(5));
            actual_od_span.setTextContent(res.getString(6));
            obs_available.setTextContent(res.getString(7));
            obs_used.setTextContent(res.getString(8));
            residuals_accepted.setTextContent(res.getString(9));
            weighted_rms.setTextContent(res.getString(10));


            comment_apogee.setTextContent(res.getString(11));
            comment_perigee.setTextContent(res.getString(12));
            comment_inclination.setTextContent(res.getString(13));
            area_pc.setTextContent(res.getString(14));
            cd_area_over_mass.setTextContent(res.getString(15));
            cr_area_over_mass.setTextContent(res.getString(16));
            thrust_acceleration.setTextContent(res.getString(17));
            sedr.setTextContent(res.getString(18));


            x.setTextContent(res.getString(19));
            y.setTextContent(res.getString(20));
            z.setTextContent(res.getString(21));
            x_dot.setTextContent(res.getString(22));
            y_dot.setTextContent(res.getString(23));
            z_dot.setTextContent(res.getString(24));


            comment_dcp_density.setTextContent(res.getString(25));
            comment_sensitivity_pos.setTextContent(res.getString(26));
            comment_sensitivity_vel.setTextContent(res.getString(27));
            cr_r.setTextContent(res.getString(28));
            ct_r.setTextContent(res.getString(29));
            ct_t.setTextContent(res.getString(30));
            cn_r.setTextContent(res.getString(31));
            cn_t.setTextContent(res.getString(32));
            cn_n.setTextContent(res.getString(33));
            crdot_r.setTextContent(res.getString(34));
            crdot_t.setTextContent(res.getString(35));
            crdot_n.setTextContent(res.getString(36));
            crdot_rdot.setTextContent(res.getString(37));
            ctdot_r.setTextContent(res.getString(38));
            ctdot_t.setTextContent(res.getString(39));
            ctdot_n.setTextContent(res.getString(40));
            ctdot_rdot.setTextContent(res.getString(41));
            ctdot_tdot.setTextContent(res.getString(42));
            cndot_r.setTextContent(res.getString(43));
            cndot_t.setTextContent(res.getString(44));
            cndot_n.setTextContent(res.getString(45));
            cndot_rdot.setTextContent(res.getString(46));
            cndot_tdot.setTextContent(res.getString(47));
            cndot_ndot.setTextContent(res.getString(48));
            cdrg_r.setTextContent(res.getString(49));
            cdrg_t.setTextContent(res.getString(50));
            cdrg_n.setTextContent(res.getString(51));
            cdrg_rdot.setTextContent(res.getString(52));
            cdrg_tdot.setTextContent(res.getString(53));
            cdrg_ndot.setTextContent(res.getString(54));
            cdrg_drg.setTextContent(res.getString(55));
            csrp_r.setTextContent(res.getString(56));
            csrp_t.setTextContent(res.getString(57));
            csrp_n.setTextContent(res.getString(58));
            csrp_rdot.setTextContent(res.getString(59));
            csrp_tdot.setTextContent(res.getString(60));
            csrp_ndot.setTextContent(res.getString(61));
            csrp_drg.setTextContent(res.getString(62));
            csrp_srp.setTextContent(res.getString(63));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //closeConn(conn);
        }

        odparameters.appendChild(comment_cov_scale);
        odparameters.appendChild(comment_exclusion_volume);
        odparameters.appendChild(time_lastob_start);
        odparameters.appendChild(time_lastob_end);
        odparameters.appendChild(recommended_od_span );
        odparameters.appendChild(actual_od_span );
        odparameters.appendChild(obs_available);
        odparameters.appendChild(obs_used);
        odparameters.appendChild(residuals_accepted);
        odparameters.appendChild(weighted_rms);

        additionalparameters.appendChild(comment_apogee);
        additionalparameters.appendChild(comment_perigee);
        additionalparameters.appendChild(comment_inclination);
        additionalparameters.appendChild(area_pc);
        additionalparameters.appendChild(cd_area_over_mass);
        additionalparameters.appendChild(cr_area_over_mass);
        additionalparameters.appendChild(thrust_acceleration) ;
        additionalparameters.appendChild(sedr);

        statevector.appendChild(x);
        statevector.appendChild(y);
        statevector.appendChild(z);
        statevector.appendChild(x_dot);
        statevector.appendChild(y_dot);
        statevector.appendChild(z_dot);

        covariancematrix.appendChild(comment_dcp_density);
        covariancematrix.appendChild(comment_sensitivity_pos);
        covariancematrix.appendChild(comment_sensitivity_vel);
        covariancematrix.appendChild(cr_r );
        covariancematrix.appendChild(ct_r );
        covariancematrix.appendChild(ct_t );
        covariancematrix.appendChild(cn_r );
        covariancematrix.appendChild(cn_t );
        covariancematrix.appendChild(cn_n );
        covariancematrix.appendChild(crdot_r );
        covariancematrix.appendChild(crdot_t );
        covariancematrix.appendChild(crdot_n );
        covariancematrix.appendChild(crdot_rdot );
        covariancematrix.appendChild(ctdot_r );
        covariancematrix.appendChild(ctdot_t );
        covariancematrix.appendChild(ctdot_n );
        covariancematrix.appendChild(ctdot_rdot );
        covariancematrix.appendChild(ctdot_tdot );
        covariancematrix.appendChild(cndot_r );
        covariancematrix.appendChild(cndot_t );
        covariancematrix.appendChild(cndot_n );
        covariancematrix.appendChild(cndot_rdot );
        covariancematrix.appendChild(cndot_tdot );
        covariancematrix.appendChild(cndot_ndot );
        covariancematrix.appendChild(cdrg_r );
        covariancematrix.appendChild(cdrg_t );
        covariancematrix.appendChild(cdrg_n );
        covariancematrix.appendChild(cdrg_rdot );
        covariancematrix.appendChild(cdrg_tdot );
        covariancematrix.appendChild(cdrg_ndot );
        covariancematrix.appendChild(cdrg_drg );
        covariancematrix.appendChild(csrp_r );
        covariancematrix.appendChild(csrp_t );
        covariancematrix.appendChild(csrp_n );
        covariancematrix.appendChild(csrp_rdot );
        covariancematrix.appendChild(csrp_tdot );
        covariancematrix.appendChild(csrp_ndot );
        covariancematrix.appendChild(csrp_drg );
        covariancematrix.appendChild(csrp_srp );

        data.appendChild(odparameters);
        data.appendChild(additionalparameters);
        data.appendChild(statevector);
        data.appendChild(covariancematrix);

        segment.appendChild(metadata(sat));
        segment.appendChild(data);

        return segment;
    }

    public Element metadata(String sat){
        Element metadata = document.createElement("metadata");

        Element comment = document.createElement("COMMENT");
        Element object = document.createElement("OBJECT");
        Element object_designator = document.createElement("OBJECT_DESIGNATOR");
        Element catalog_name = document.createElement("CATALOG_NAME");
        Element object_name = document.createElement("OBJECT_NAME");
        Element international_designator = document.createElement("INTERNATIONAL_DESIGNATOR");
        Element object_type = document.createElement("OBJECT_TYPE");
        Element operator_contact_position = document.createElement("OPERATOR_CONTACT_POSITION");
        Element operator_organization = document.createElement("OPERATOR_ORGANIZATION");
        Element operator_phone = document.createElement("OPERATOR_PHONE");
        Element operator_email = document.createElement("OPERATOR_EMAIL");
        Element ephemeris_name = document.createElement("EPHEMERIS_NAME");
        Element covariance_method = document.createElement("COVARIANCE_METHOD");
        Element maneuverable = document.createElement("MANEUVERABLE");
        Element ref_frame = document.createElement("REF_FRAME");
        Element gravity_model = document.createElement("GRAVITY_MODEL");
        Element atmospheric_model = document.createElement("ATMOSPHERIC_MODEL");
        Element n_body_perturbations = document.createElement("N_BODY_PERTURBATIONS");
        Element solar_rad_pressure = document.createElement("SOLAR_RAD_PRESSURE");
        Element earth_tides = document.createElement("EARTH_TIDES");
        Element intrack_thrust = document.createElement("INTRACK_THRUST");


        try {
            String query ="";
            if(sat.equals("SAT1")) {
                query = "select SAT1_COMMENT_SCREENING_DATA_SOURCE,SAT1_OBJECT,SAT1_OBJECT_DESIGNATOR,SAT1_CATALOG_NAME,SAT1_OBJECT_NAME,SAT1_INTERNATIONAL_DESIGNATOR,SAT1_OBJECT_TYPE,SAT1_OPERATOR_CONTACT_POSITION,SAT1_OPERATOR_ORGANIZATION,SAT1_OPERATOR_PHONE,SAT1_OPERATOR_EMAIL,SAT1_EPHEMERIS_NAME,SAT1_COVARIANCE_METHOD,SAT1_MANEUVERABLE,SAT1_REF_FRAME,SAT1_GRAVITY_MODEL,SAT1_ATMOSPHERIC_MODEL,SAT1_N_BODY_PERTURBATIONS,SAT1_SOLAR_RAD_PRESSURE,SAT1_EARTH_TIDES,SAT1_INTRACK_THRUST from cdm where message_id = \"" + mid + "\"";
            } else if (sat.equals("SAT2")){
                query = "select SAT2_COMMENT_SCREENING_DATA_SOURCE,SAT2_OBJECT,SAT2_OBJECT_DESIGNATOR,SAT2_CATALOG_NAME,SAT2_OBJECT_NAME,SAT2_INTERNATIONAL_DESIGNATOR,SAT2_OBJECT_TYPE,SAT2_OPERATOR_CONTACT_POSITION,SAT2_OPERATOR_ORGANIZATION,SAT2_OPERATOR_PHONE,SAT2_OPERATOR_EMAIL,SAT2_EPHEMERIS_NAME,SAT2_COVARIANCE_METHOD,SAT2_MANEUVERABLE,SAT2_REF_FRAME,SAT2_GRAVITY_MODEL,SAT2_ATMOSPHERIC_MODEL,SAT2_N_BODY_PERTURBATIONS,SAT2_SOLAR_RAD_PRESSURE,SAT2_EARTH_TIDES,SAT2_INTRACK_THRUST from cdm where message_id = \"" + mid + "\"";
            }

            ResultSet res= state.executeQuery(query);

            comment.setTextContent(res.getString(1));
            object.setTextContent(res.getString(2));
            object_designator.setTextContent(res.getString(3));
            catalog_name.setTextContent(res.getString(4));
            object_name.setTextContent(res.getString(5));
            international_designator.setTextContent(res.getString(6));
            object_type.setTextContent(res.getString(7));
            operator_contact_position.setTextContent(res.getString(8));
            operator_organization.setTextContent(res.getString(9));
            operator_phone.setTextContent(res.getString(10));
            operator_email.setTextContent(res.getString(11));
            ephemeris_name.setTextContent(res.getString(12));
            covariance_method.setTextContent(res.getString(13));
            maneuverable.setTextContent(res.getString(14));
            ref_frame.setTextContent(res.getString(15));
            gravity_model.setTextContent(res.getString(16));
            atmospheric_model.setTextContent(res.getString(17));
            n_body_perturbations.setTextContent(res.getString(18));
            solar_rad_pressure.setTextContent(res.getString(19));
            earth_tides.setTextContent(res.getString(20));
            intrack_thrust.setTextContent(res.getString(21));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }

        metadata.appendChild(comment);
        metadata.appendChild(object);
        metadata.appendChild(object_designator);
        metadata.appendChild(catalog_name);
        metadata.appendChild(object_name);
        metadata.appendChild(international_designator);
        metadata.appendChild(object_type);
        metadata.appendChild(operator_contact_position);
        metadata.appendChild(operator_organization);
        metadata.appendChild(operator_phone);
        metadata.appendChild(operator_email);
        metadata.appendChild(ephemeris_name);
        metadata.appendChild(covariance_method);
        metadata.appendChild(maneuverable);
        metadata.appendChild(ref_frame);
        metadata.appendChild(gravity_model);
        metadata.appendChild(atmospheric_model);
        metadata.appendChild(n_body_perturbations);
        metadata.appendChild(solar_rad_pressure);
        metadata.appendChild(earth_tides);
        metadata.appendChild(intrack_thrust);

        return metadata;
    }

    public static void main(String args[]){
       // new Db_Json2XML().json2xml("000029268_conj_000002088_2020249020027_247124551540");
    }
}
