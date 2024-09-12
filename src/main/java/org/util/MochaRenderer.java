package org.util;

import static org.lwjgl.opengl.GL11.*;

public class MochaRenderer {
    private Texture text;
    private MochaTextureHandler mochaTextureHandler;
    private float[] size;
    private float[] position;

    public MochaRenderer(Texture text, float [] size, float [] position, MochaTextureHandler mochaTextureHandler){
        this.text = text;
        this.size = size;
        this.position = position;

        this.mochaTextureHandler = mochaTextureHandler;

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
        this.position = new float[] {position[0] * 0.0001f, position[1] * 0.0001f};
    }

    public void renderText(){
        text.bind();

        // Apply transformations (translation and scaling) based on position and size
        glPushMatrix();
        glTranslatef(position[0], position[1], 0);
        glScalef(size[0], size[1], 1);
        mochaTextureHandler.render();

        glPopMatrix();

        text.unbind();
    }

}
