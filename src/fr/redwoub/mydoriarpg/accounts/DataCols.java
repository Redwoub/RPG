package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataCols extends AbstractData {
    //cols = money in the game
    private long cols;

    public DataCols(UUID uuid){
        this.uuid = uuid;
    }

    public void setCols(long cols){
        if(cols < 0){
            this.cols = 0;
            return;
        }

        this.cols = cols;
    }

    public Long getCols(){
        return cols;
    }

    public void addCols(long cols){
        this.cols += cols;
    }

    public void removeCols(long cols){
        if(cols > this.cols){
            this.cols = 0;
            return;
        }

        this.cols -= cols;
    }

    public void resetCols(){
        this.cols = 0;
    }
}
