package com.itheima.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserServiceImpl3 {


    private List<String> names;
    private Set<String> gameNames;
    private Map<String,String> maps;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Set<String> getGameNames() {
        return gameNames;
    }

    public void setGameNames(Set<String> gameNames) {
        this.gameNames = gameNames;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }
}
