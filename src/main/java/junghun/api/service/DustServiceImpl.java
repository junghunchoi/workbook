package junghun.api.service;


import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DustServiceImpl {

    private final ModelMapper modelMapper;
    private final DustServiceImpl dustRepository;

}
