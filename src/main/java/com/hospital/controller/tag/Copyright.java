package com.hospital.controller.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Copyright extends SimpleTagSupport {

    private static final Logger logger = LogManager.getLogger(Copyright.class);

    private static final String COPYRIGHT = "<p>Copyright by Anton Brunner 2021 </p>";
    private static final String START_COPYRIGHT = "<footer>";
    private static final String END_COPYRIGHT = "</footer>";

//    @Override
//    public int doStartTag() throws JspException {
//        JspWriter out = pageContext.getOut();
//        try {
//            out.write(START_COPYRIGHT);
//            out.write(COPYRIGHT);
//        } catch (IOException e) {
//            logger.log(Level.WARN, e);
//            throw new JspException(e);
//        }
//        return SKIP_BODY;
//    }
//
//    @Override
//    public int doEndTag() throws JspException {
//        JspWriter out = pageContext.getOut();
//        try {
//            out.write(END_COPYRIGHT);
//        } catch (IOException e) {
//            logger.log(Level.WARN, e);
//            throw new JspException(e);
//        }
//        return EVAL_PAGE;
//    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println(START_COPYRIGHT+COPYRIGHT+END_COPYRIGHT);
    }
}
