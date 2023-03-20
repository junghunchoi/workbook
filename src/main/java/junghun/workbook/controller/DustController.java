//package junghun.workbook.controller;
//
//
//
//import java.io.IOException;
//import junghun.workbook.dto.DustDTO;
//import junghun.workbook.service.DustService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//@RequestMapping("/dust")
//@Log4j2
//@RequiredArgsConstructor
//public class DustController {
//
//    private final DustService dustService;
//
//
//    @GetMapping("/list")
//    public
//    void ChartDust(Model model) throws IOException, ParseException {
//
//        DustDTO dustDTO = DustDTO.builder().build();
//
//        DustDTO jsonArray = dustService.returnList(dustDTO);
//
//        model.addAttribute("dustDTO", dustDTO);
//
//        log.info(model.getClass().getName());
////        return jsonArray;
//    }
//
//}
