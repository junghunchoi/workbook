package junghun.api.controller;



import java.io.IOException;
import junghun.api.service.DustService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dust")
@Log4j2
@RequiredArgsConstructor
public class DustController {

    private final DustService dustService;

    public void ChartDust(Model model) throws IOException, ParseException {

        JSONArray jsonArray = dustService.returnList(1);

        model.addAttribute("list", jsonArray);
    }

}
