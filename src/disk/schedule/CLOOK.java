package disk.schedule;

import java.util.ArrayList;

/**
 * CLOOK
 *
 * @author wz
 * @date 2015年12月29
 */
public class CLOOK {
    private Integer cTrack;
    private ArrayList<Integer> tracks;
    private static final Integer MAX_TRACK = 200;

    public CLOOK(Integer cTrack, ArrayList<Integer> tracks) {
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

        if (divide > 0) {
            seekLength +=cTrack - tracks.get(0);
            cTrack = tracks.get(0);
        }

        for (int i = 1; i < divide; i++) {
            seekLength += tracks.get(i) - cTrack;
            cTrack = tracks.get(i);
        }

        double avgSeekLength = (double) seekLength / tracks.size();
        System.out.println("平均寻道长度" + avgSeekLength);

    }

}
