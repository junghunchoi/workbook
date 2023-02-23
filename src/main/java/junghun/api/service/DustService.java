package junghun.api.service;

import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public interface DustService {

    JSONArray returnList(int num) throws IOException, ParseException;

}
