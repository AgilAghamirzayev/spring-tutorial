package app.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorHistoryService {

    private final Map<String, List<Operation>> history = new HashMap<>();

    public void put(String sessionid, Operation op) {
        List<Operation> operations = history.getOrDefault(sessionid, new ArrayList<>());
        operations.add(op);
        history.put(sessionid, operations);
    }

    public List<Operation> get(String sessionid) {
        return history.get(sessionid);
    }
}
