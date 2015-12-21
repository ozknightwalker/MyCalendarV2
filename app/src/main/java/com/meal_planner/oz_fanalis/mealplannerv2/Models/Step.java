package com.meal_planner.oz_fanalis.mealplannerv2.Models;

import java.io.Serializable;
import java.util.Random;

public class Step implements Serializable {

    private long pk;
    private int sequence;
    private String instruction;

    public Step(int sequence, String instruction) {
        this.pk = new Random().nextLong();
        this.sequence = sequence;
        this.instruction = instruction;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
