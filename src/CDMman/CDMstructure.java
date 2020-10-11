package CDMman;

import java.util.HashMap;

public class CDMstructure {
    HashMap<String, String> CDM_STRUCTURE = new HashMap<String, String>();
    public HashMap CDMstruct(String loc){
        CDM_STRUCTURE = new ReadConfig().read(loc);

        return CDM_STRUCTURE;
    }
}
