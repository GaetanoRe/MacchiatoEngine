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

    private int indexID; // The ID of the Indicies of the Texture

    private FloatBuffer vertexBuffer;

    private FloatBuffer textureBuffer;


    public MochaTextureHandler(float [] verticies, float [] textureCoords, int [] indicies){
        drawCount = indicies.length;

        // generate a buffer id for the vertex information of the image and store it on the GPU
        vertexId = glGenBuffers();
        vertexBuffer = createBuffer(verticies);
        glBindBuffer(GL_ARRAY_BUFFER, vertexId);
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer , GL_STATIC_DRAW);


        textureID = glGenBuffers();
        textureBuffer = createBuffer(textureCoords);
        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);

        indexID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexID);
        IntBuffer indBuffer = BufferUtils.createIntBuffer(indicies.length);
        indBuffer.put(indicies);
        indBuffer.flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        indexID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexID);

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indBuffer, GL_STATIC_DRAW);
    }

    public void render(){
        glEnable(GL_TEXTURE_2D);


        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vertexId);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexID);
        glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
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
