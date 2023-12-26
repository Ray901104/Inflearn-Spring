package spring.aop.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.aop.example.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
