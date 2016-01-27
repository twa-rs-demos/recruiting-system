package com.thoughtworks.twars.mapper;

import com.thoughtworks.twars.bean.Paper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaperMapperTest extends TestBase {

    private PaperMapper paperMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        paperMapper = session.getMapper(PaperMapper.class);
    }

    @Test
    public void should_return_all_papers() throws Exception {
        List<Paper> papers = paperMapper.findAll();
        assertThat(papers.size(), is(4));
        assertThat(papers.get(0).getMakerId(), is(1));
    }

    @Test
    public void should_return_paper_with_data() throws Exception {
        Paper paper = paperMapper.getOnePaper(1);

        assertThat(paper.getId(), is(1));
        assertThat(paper.getMakerId(), is(1));
        paper.getResponseInfo();
        assertThat(paper.getSections().size(), is(2));

    }

    @Test
    public void should_insert_paper() {
        Paper paper = new Paper();
        paper.setMakerId(3);

        paperMapper.insertPaper(paper);

        assertThat(paper.getId(), is(5));
    }
}