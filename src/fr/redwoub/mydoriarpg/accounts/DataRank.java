package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataRank extends AbstractData {

    private RankUnit rank;

    public DataRank(UUID uuid){
        this.uuid = uuid;
    }

    public void setRank(RankUnit rank) {
        this.rank = rank;
    }

    public RankUnit getRank() {
        return rank;
    }
}
