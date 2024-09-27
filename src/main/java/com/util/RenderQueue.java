package com.util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RenderQueue {

    private PriorityQueue<MochaRenderer> renderers;

    public RenderQueue(){
        renderers = new PriorityQueue<MochaRenderer>(new Comparator<MochaRenderer>() {
            @Override
            public int compare(MochaRenderer o1, MochaRenderer o2) {
                return Integer.compare(o1.getzIndex(), o2.getzIndex());
            }
        });
    }

    public void setPositionAtZ(int z, float [] position){
        for(MochaRenderer renderer : renderers){
            if(z == renderer.getzIndex()){
                renderer.setPosition(position);
            }
        }
    }

    public float [] getPositionAtZ(int z){
        for(MochaRenderer renderer : renderers){
            if(z == renderer.getzIndex()) {
                return renderer.getPosition();
            }
        }

        return null;
    }

    public void addRenderItem(Texture t, float [] size, float [] position, MochaTextureHandler th){
        renderers.add(new MochaRenderer(t, size, position, th));
    }

    public void renderAll(){
        for(MochaRenderer r : renderers){
            r.renderText();
        }
    }


}
