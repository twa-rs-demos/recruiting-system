package com.thoughtworks.twars.resource;

import com.thoughtworks.twars.bean.Paper;
import com.thoughtworks.twars.mapper.BlankQuizMapper;
import com.thoughtworks.twars.mapper.PaperMapper;
import com.thoughtworks.twars.mapper.SectionMapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;


@Path("/papers")
public class PaperResource {

    @Inject
    private PaperMapper paperMapper;
    @Inject
    private SectionMapper sectionMapper;
    @Inject
    private BlankQuizMapper blankQuizMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPapers() {

        List<Paper> papers = paperMapper.findAll();
        List<Map> result = new ArrayList<>();

        for (int i = 0; i < papers.size(); i++) {
            Paper item = papers.get(i);
            Map<String, String> map = new HashMap<>();
            map.put("uri", "papers/" + item.getId());
            result.add(map);
        }

        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOnePaper(@PathParam("param") int id) {

        List<Map> sectionList = sectionMapper.getSectionsByPaperId(id)
                .stream()
                .map(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("desc", item.getDescription());
                    map.put("quizzes", getQuizzesBySectionId(item.getId()));
                    return map;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("sections", sectionList);
        result.put("id", id);
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/enrollment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOnePaper() {
        return getOnePaper(1);
    }

    private List<Map> getQuizzesBySectionId(int sectionId) {

        String basePath = "/blankQuizzes/";

        return blankQuizMapper.findBySectionId(sectionId)
                .stream()
                .map(b -> {
                    HashMap<String, Object> item = new HashMap<>();
                    item.put("id", b.getId());

                    item.put("definition", buildURIMap(basePath + b.getId()));
                    item.put("items", buildURIMap(basePath + b.getId() + "/items"));
                    return item;
                })
                .collect(Collectors.toList());
    }

    private Map<String, String> buildURIMap(String uri) {
        Map<String, String> result = new HashMap<>();
        result.put("uri", uri);
        return result;
    }
}


