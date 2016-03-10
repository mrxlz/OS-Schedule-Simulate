package disk.schedule;

import java.util.ArrayList;

/**
 * LOOK
 *
 * @author wz
 * @date 2015年12月29
 */
public class LOOK {
    private Integer cTrack;
    private ArrayList<Integer> tracks;
    private static final Integer MAX_TRACK = 200;

    public LOOK(Integer cTrack, ArrayList<Integer> tracks) {
        this.cTrack = cTrack;
        this.tracks = tracks;
    }

    public void schedule() {
        int seekLength = 0;
        tracks.sort((track1, track2) -> track1.compareTo(track2));
        int divide = 0;
        while (tracks.get(divide) < cTrack)
            divide++;

        for (int i = divide; i < tracks.size(); i++) {
            seekLength += tracks.get(i) - cTrack;
            cTrack = tracks.get(i);
        }

        for (int i = divide - 1; i >= 0; i++) {
            seekLength += cTrack - tracks.get(i);
            cTrack = tracks.get(i);
        }

        double avgSeekLength = (double) seekLength / tracks.size();
        System.out.println("平均寻道长度" + avgSeekLength);

    }

}
