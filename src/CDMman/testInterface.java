package CDMman;

public interface testInterface {
    String baseSPurl = "https://www.space-track.org/";
    String queryCDM = "expandedspacedata/query/class/cdm/CREATION_DATE/>%s/orderby/CONSTELLATION asc/limit/100/format/json/emptyresult/show";
    String SPauthurl = "/ajaxauth/login";
    String SPauthformat = "identity=%s&password=%s";
}
