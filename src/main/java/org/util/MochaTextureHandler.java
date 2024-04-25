package org.util;
import org.lwjgl.BufferUtils;

import java.nio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class MochaTextureHandler {
    private int drawCount; // The amount of times that the program will draw (dependant on the shape)
    private int vertexId; // The id of the vertex of the image in the context of the window. This will be binded
                          // and will be stored in the GPU


    private int textureID; // The id of the texture that will be stored on the GPU

    private FloatBuffer vertexBuffer;

    private FloatBuffer textureBuffer;


    public MochaTextureHandler(float [] verticies, float [] textureCoords){
        drawCount = verticies.length / 3;

        // generate a buffer id for the vertex information of the image and store it on the GPU
        vertexId = glGenBuffers();
        vertexBuffer = createBuffer(verticies);
        glBindBuffer(GL_ARRAY_BUFFER, vertexId);
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer , GL_STATIC_DRAW);


        textureID = glGenBuffers();
        textureBuffer = createBuffer(textureCoords);
        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void render(){
        glEnable(GL_TEXTURE_2D);
        glBindBuffer(GL_ARRAY_BUFFER, vertexId);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);

        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glDrawArrays(GL_TRIANGLES, 0, drawCount);

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDisable(GL_TEXTURE_2D);
    }

    private FloatBuffer createBuffer(float [] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
