package chapter11.item81;

/**
 * author : haedoang
 * date : 2022/07/12
 * description :
 */
public class Data {
    private String packet;

    private boolean transfer = true;

    public synchronized String receive() {
        while (transfer) {
            try {
                System.out.println(Thread.currentThread().getName() + " wait()");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted!");
            }
        }
        transfer = true;

        String returnPacket = packet;
        notifyAll();
        return returnPacket;
    }

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }
}
