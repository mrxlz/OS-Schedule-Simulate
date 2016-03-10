package disk.schedule;

import java.util.ArrayList;

/**
 * SSTF
 *
 * @author wz
 * @date 2015年12月29
 */
public class SSTF {
    private Integer cTrack;
    private ArrayList<Integer> tracks;

    public SSTF(Integer cTrack, ArrayList<Integer> tracks) {
        this.cTrack = cTrack;
        this.tracks = tracks;
    }

    public void schedule() {
        int seekLength = 0;
        tracks.sort((track1, track2) -> track1.compareTo(track2));
        for (int i = 0; i < tracks.size(); i++) {
            seekLength += Math.abs(tracks.get(i) - cTrack);
            cTrack = tracks.get(i);
        }
        double avgSeekLength = (double) seekLength / tracks.size();
        System.out.println("平均寻道长度" + avgSeekLength);
    }
}
