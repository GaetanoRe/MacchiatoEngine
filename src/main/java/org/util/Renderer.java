package org.util;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public Renderer(){
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
    }

    public void renderText(Texture t, float x , float y, float width, float height){
        // Rendering Handling here
        t.bind();

        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0);
            glVertex2f((width * -1) + x, height + y);

            glTexCoord2f(1,0);
            glVertex2f(width + x, height + y);

            glTexCoord2f(1,1);
            glVertex2f(width + x, (-1 * height) + y);

            glTexCoord2f(0,1);
            glVertex2f((-1 * width) + x, (-1 * height) + y);

        }
        glEnd();

    }
}
