package dev.cnbetty.core.utility.vector;

import java.util.Arrays;

public class Vector {
    private float[] components;

    public Vector(final float... components) {
        this.components = new float[components.length];
        for (int i = 0; i < components.length; i++) {
            this.components[i] = components[i];
        }
    }

    public static float[] toArray(Vector vector) {
        float[] output = new float[vector.getComponents().length];
        for (int i = 0; i < vector.getComponents().length; i++) {
            output[i] = vector.getComponents()[i];
        }
        return output;
    }

    public float[] getComponents() {
        return this.components;
    }

    public Vector add(Vector other) {
        float[] result = new float[getComponents().length];
        for (int i = 0; i < getComponents().length; i++) {
            result[i] = this.getComponents()[i] + other.getComponents()[i];
        }
        return new Vector(result);
    }
    public Vector subtract(Vector other) {
        float[] result = new float[getComponents().length];
        for (int i = 0; i < getComponents().length; i++) {
            result[i] = this.getComponents()[i] - other.getComponents()[i];
        }
        return new Vector(result);
    }
    public float dot(Vector other) {
        float result = 0;
        for (int i = 0; i < getComponents().length; i++) {
            result += this.getComponents()[i] * other.getComponents()[i];
        }
        return result;
    }
    public Vector cross(Vector other) {
        if (getComponents().length != 3 || other.getComponents().length != 3) {
            throw new IllegalArgumentException("Cross product is only defined for 3D vectors.");
        }
        float[] result = new float[other.getComponents().length];
        for (int i = 0; i<getComponents().length; i++) {
            result[0] = this.getComponents()[1] * other.getComponents()[2] - this.getComponents()[2] * other.getComponents()[1];
            result[1] = this.getComponents()[2] * other.getComponents()[0] - this.getComponents()[0] * other.getComponents()[2];
            result[2] = this.getComponents()[0] * other.getComponents()[1] - this.getComponents()[1] * other.getComponents()[0];
        }
        
        return new Vector(result);
    }
    public float magnitude() {
        float sum = 0;
        for (float component : getComponents()) {
            sum += component * component;
        }
        return (float) Math.sqrt((double) sum);
    }
    public Vector normalize() {
        float magnitude = magnitude();
        float[] result = new float[getComponents().length];
        for (int i = 0; i < getComponents().length; i++) {
            result[i] = getComponents()[i] / magnitude;
        }
        return new Vector(result);
    }
    public String toString() {
        return Arrays.toString(getComponents());
    }
}
