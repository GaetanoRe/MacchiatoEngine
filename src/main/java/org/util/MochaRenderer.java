package org.util;

public class MochaRenderer {
    private Texture text;
    private MochaTextureHandler mochaTextureHandler;
    private float[] size;
    private float[] position;

    public MochaRenderer(Texture text, float [] size, float [] position){
        this.text = text;
        this.size = size;
        this.position = position;
        float [] verticies = new float[] {
                -0.05f, 0.05f, 0, // TOP LEFT
                0.05f, 0.05f, 0, // TOP RIGHT
                0.05f, -0.05f, 0, // BOTTOM RIGHT
                -0.05f, -0.05f, 0, // BOTTOM LEFT
        };
        float [] textureCoords = new float []{
                0, 0,  // top left
                1, 0,  // top right
                1, 1,  // bottom right
                0, 1,  // bottom left

        };

        int [] indicies = new int []{
                0,1,2,
                2,3,0
        };

        mochaTextureHandler = new MochaTextureHandler(verticies, textureCoords, indicies);

    }


    public Texture getTexture(){
        return text;
    }


    public void setTexture(Texture text){
        this.text = text;
    }

    public float [] getSize(){
        return size;
    }

    public void setSize(float [] size){
        this.size = size;
    }

    public float [] getPosition(){
        return position;
    }

    public void setPosition(float [] position){
        this.position = position;
    }

}
