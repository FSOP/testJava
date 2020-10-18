package Bottom.ReportMaker;

public interface InterfaceCriteria {

    // dimensionless
    float criteria_leo_probability = (float) 0.0000000001;

    // meter
    float criteria_leo_distance = (float) 5000;

    // meter
    float criteria_geo_distance = (float) 5000;

    // LEO Period = unit minute
    float criteria_leo_period = (float) 1000;

    String report_xml_loc = "C:\\Users\\ukouu\\IdeaProjects\\";
    String cdm_xml_loc = "C:\\Users\\ukouu\\IdeaProjects\\";

}
