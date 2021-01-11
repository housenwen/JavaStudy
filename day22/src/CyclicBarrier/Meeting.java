package CyclicBarrier;

class MeetingThread extends Thread {
    @Override
    public void run() {
        System.out.println("好了，人都到了，开始开会......");
    }
}
