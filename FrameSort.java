import java.util.*;

public class FrameSort {
    static class Frame {
        String content;
        int fnum;

        Frame(String content, int fnum) {
            this.content = content;
            this.fnum = fnum;
        }
    }

    public void sort(Frame[] frames) {
        int n = frames.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (frames[j].fnum > frames[j + 1].fnum) {
                    String s1 = frames[j].content, s2 = frames[j + 1].content;
                    int n1 = frames[j].fnum, n2 = frames[j + 1].fnum;
                    frames[j].fnum = n2;
                    frames[j + 1].fnum = n1;
                    frames[j].content = s2;
                    frames[j + 1].content = s1;
                }
            }
        }
    }

    public static void display(Frame[] frames) {
        for (Frame frame : frames)
            System.out.println("Frame: " + frame.fnum + " Content: " + frame.content);
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int n = sc.nextInt();

        System.out.println("Enter the frame details: \n");
        Frame[] frames = new Frame[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter frame number:");
            int fnum = sc.nextInt();
            System.out.print("Enter frame content:");
            String content = sc.next();
            frames[i] = new Frame(content, fnum);
        }

        ArrayList<Frame> frameList = new ArrayList<>(Arrays.asList(frames));
        Collections.shuffle(frameList);
        frames = frameList.toArray(new Frame[0]);

        System.out.println("\nFrames before sorting\n");
        display(frames);

        FrameSort fs = new FrameSort();
        fs.sort(frames);

        System.out.println("\nFrames After sorting\n");
        display(frames);
        sc.close();
    }
}