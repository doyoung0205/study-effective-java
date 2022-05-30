package study.heejin.chapter8.item55;

import java.util.Optional;

public class ParentPid {

    public static void main(String[] args) {
        ProcessHandle ph = ProcessHandle.current();
        Optional<ProcessHandle> parentProcess = ph.parent();

        // isPresent 를 적절하지 못하게 사용한 예
        System.out.println("부모 PID: " +
                (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A")
        );

        System.out.println("==========");

        // Optional의 map를 사용한 예
        System.out.println("부모 PID: " +
                parentProcess.map(h -> String.valueOf(h.pid())).orElse("N/A")
        );
    }
}
