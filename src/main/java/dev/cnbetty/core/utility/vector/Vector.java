package dev.cnbetty.core.utility.vector;

import java.util.Arrays;

public class Vector {
    private final float[] components;

    public Vector(final float... components) {
        this.components = new float[components.length];
        System.arraycopy(components, 0, this.components, 0, components.length);
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

    public Vector scalar(float factor) {
        float[] newcomponents = new float[3];
        for (int i = 0; i < 3; i++) {
            newcomponents[i] = this.getComponents()[i] * factor;
        }
        return new Vector(newcomponents);
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
        for (int i = 0; i < getComponents().length; i++) {
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
        return (float) Math.sqrt(sum);
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
