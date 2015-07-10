/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.32
 * Generated at: 2014-07-07 07:42:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.opensolaris.opengrok.search.Results;
import org.opensolaris.opengrok.web.SearchHelper;
import org.opensolaris.opengrok.web.SortOrder;
import org.opensolaris.opengrok.web.Suggestion;
import java.util.TreeSet;
import org.opensolaris.opengrok.web.Prefix;
import org.opensolaris.opengrok.web.PageConfig;
import org.opensolaris.opengrok.configuration.Project;
import org.opensolaris.opengrok.Info;
import org.opensolaris.opengrok.web.PageConfig;
import org.opensolaris.opengrok.web.PageConfig;
import org.opensolaris.opengrok.web.SearchHelper;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import org.opensolaris.opengrok.configuration.Project;
import org.opensolaris.opengrok.search.QueryBuilder;
import org.opensolaris.opengrok.web.PageConfig;
import org.opensolaris.opengrok.web.Prefix;
import org.opensolaris.opengrok.web.Util;
import java.util.EnumSet;
import org.opensolaris.opengrok.web.PageConfig;
import org.opensolaris.opengrok.web.Prefix;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    private StringBuilder createUrl(SearchHelper sh, boolean menu) {
        StringBuilder url = new StringBuilder(64);
        QueryBuilder qb = sh.builder;
        if (menu) {
            url.append("search?");
        } else {
            Util.appendQuery(url, "sort", sh.order.toString());
        }
        if (qb != null) {
            Util.appendQuery(url, "q", qb.getFreetext());
            Util.appendQuery(url, "defs", qb.getDefs());
            Util.appendQuery(url, "refs", qb.getRefs());
            Util.appendQuery(url, "path", qb.getPath());
            Util.appendQuery(url, "hist", qb.getHist());
            Util.appendQuery(url, "type", qb.getType());
        }
        if (sh.projects != null && sh.projects.size() != 0) {
            Util.appendQuery(url, "project", cfg.getRequestedProjectsAsString());
        }
        return url;
    }


    private PageConfig cfg;


/* ---------------------- foot.jspf members start --------------------- */
    private static EnumSet<Prefix> needAddDiv = EnumSet.of(
        Prefix.HIST_L, Prefix.XREF_P, Prefix.DIFF_P, Prefix.MORE_P,
        // could NOT find any mapping/reference to those ones:
        Prefix.HIST_S, Prefix.XREF_S, Prefix.DIFF_S, Prefix.MORE_S);
/* ---------------------- foot.jspf members end --------------------- */

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("/menu.jspf", Long.valueOf(1396943084000L));
    _jspx_dependants.put("/pageheader.jspf", Long.valueOf(1396943084000L));
    _jspx_dependants.put("/foot.jspf", Long.valueOf(1396943084000L));
    _jspx_dependants.put("/pageconfig.jspf", Long.valueOf(1396943084000L));
    _jspx_dependants.put("/httpheader.jspf", Long.valueOf(1396943084000L));
    _jspx_dependants.put("/projects.jspf", Long.valueOf(1396943084000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;


/* ---------------------- projects.jspf start --------------------- */
{
    // Use UTF-8 if no encoding is specified in the request
    if (request.getCharacterEncoding() == null) {
        request.setCharacterEncoding("UTF-8");
    }

    cfg = PageConfig.get(request);
    cfg.getEnv().setUrlPrefix(request.getContextPath() + Prefix.SEARCH_R + "?");

    String projects = cfg.getRequestedProjectsAsString();
    if (projects.length() != 0) {
        Cookie cookie = new Cookie("OpenGrokProject", projects);
        response.addCookie(cookie);
    }
}
/* ---------------------- projects.jspf end --------------------- */


/* ---------------------- search.jsp start --------------------- */
{
    cfg = PageConfig.get(request);

    long starttime = System.currentTimeMillis();

    SearchHelper searchHelper = cfg.prepareSearch()
        .prepareExec(cfg.getRequestedProjects()).executeQuery().prepareSummary();
    if (searchHelper.redirect != null) {
        response.sendRedirect(searchHelper.redirect);
    }
    if (searchHelper.errorMsg != null) {
        cfg.setTitle("Search Error");
    } else {
        cfg.setTitle("Search");
    }
    response.addCookie(new Cookie("OpenGrokSorting", searchHelper.order.toString()));


/* ---------------------- httpheader.jsp start --------------------- */
{
    cfg = PageConfig.get(request);
    String styleDir = cfg.getCssDir();
    String ctxPath = request.getContextPath();
    String dstyle = styleDir + '/' + "style.css";
    String pstyle = styleDir + '/' + "print.css";

      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n");
      out.write("    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta name=\"robots\" content=\"noindex,nofollow\" />\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<meta name=\"generator\" content=\"{OpenGrok ");
      out.print(Info.getVersion());
      out.write(' ');
      out.write('(');
      out.print(Info.getRevision());
      out.write(")\" />\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />\n");
      out.write("<link rel=\"icon\" href=\"");
      out.print(styleDir);
      out.write("/img/icon.png\" type=\"image/png\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" media=\"all\"\n");
      out.write("    title=\"Default\" href=\"");
      out.print( dstyle );
      out.write("\" />\n");
      out.write("<link rel=\"alternate stylesheet\" type=\"text/css\" media=\"all\"\n");
      out.write("    title=\"Paper White\" href=\"");
      out.print( pstyle );
      out.write("\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(styleDir);
      out.write("/print.css\" media=\"print\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(styleDir);
      out.write("/jquery.tooltip.css\" />\n");
      out.write("\n");
      out.write("<link rel=\"search\" href=\"");
      out.print(ctxPath);
      out.write("/opensearch\"\n");
      out.write("    type=\"application/opensearchdescription+xml\"\n");
      out.write("    title=\"OpenGrok Search for current project(s)\" />\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(ctxPath);
      out.write("/jquery-1.4.4.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(ctxPath);
      out.write("/jquery.tooltip-1.3.pack.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(ctxPath);
      out.write("/utils.js\"></script>\n");
      out.write("<title>");
      out.print(cfg.getTitle());
      out.write("</title>");

    out.write(cfg.getHeaderData());

      out.write("\n");
      out.write("</head>");

}
/* ---------------------- httpheader.jsp end --------------------- */

      out.write("<body>\n");
      out.write("<div id=\"page\">\n");
      out.write("    <div id=\"whole_header\">\n");
      out.write("        <div id=\"header\">");
 
/* ---------------------- pageheader.jspf start --------------------- */
{
    cfg = PageConfig.get(request);

      out.write("\n");
      out.write("<a href=\"");
      out.print( request.getContextPath() );
      out.write("/\"><span id=\"MastheadLogo\"></span></a>\n");
      out.print( cfg.getEnv().getConfiguration().getHeaderIncludeFileContent() );
      out.write('\n');
 
}
/* ---------------------- pageheader.jspf end --------------------- */

      out.write('\n');
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div id=\"Masthead\"></div>\n");
      out.write("        <div id=\"bar\">\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"");
      out.print( request.getContextPath()
                    );
      out.write("/\"><span id=\"home\"></span>Home</a></li>\n");
      out.write("            </ul>\n");
      out.write("            ");
      out.write("\n");
      out.write("            <div id=\"sortfield\">\n");
      out.write("                <label for=\"sortby\">Sort by</label>\n");
      out.write("                <ul id=\"sortby\">");

    StringBuilder url = createUrl(searchHelper, true).append("&amp;sort=");
    for (SortOrder o : SortOrder.values()) {
        if (searchHelper.order == o) {
                    
      out.write("<li><span class=\"active\">");
      out.print( o.getDesc() );
      out.write("</span></li>");

        } else {
                    
      out.write("<li><a href=\"");
      out.print( url );
      out.print( o );
      out.write('"');
      out.write('>');
      out.print( o.getDesc() );
      out.write("</a></li>");

        }
    }
                
      out.write("</ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"menu\">");
      out.write('\n');
      out.write('\n');

/* ---------------------- menu.jspf start --------------------- */
{
    // PageConfig cfg = PageConfig.get(request);

    List<Project> projects = cfg.getEnv().getProjects();
    if (projects == null) {
        projects = new ArrayList<Project>(0);
    }
    TreeMap<String, String> pMap = new TreeMap<String, String>();
    QueryBuilder queryParams = cfg.getQueryBuilder();
    if (projects.size() != 0) {
        for (Project p : projects) {
            String name = p.getDescription();
            String esc = Util.formQuoteEscape(p.getDescription());
            pMap.put(name, esc);
        }
        StringBuilder jsProjects = new StringBuilder(64);
        jsProjects.append('\'');
        for (String name : cfg.getRequestedProjects()) {
            jsProjects.append(name).append("','");
        }
        if (jsProjects.length() > 1) {
            jsProjects.setLength(jsProjects.length()-2);
        } else {
            jsProjects.setLength(0);
        }

      out.write("\n");
      out.write("<script type=\"text/javascript\">/* <![CDATA[ */\n");
      out.write("    document.projects = [ ");
      out.print( Util.encode(jsProjects.toString())
    );
      out.write(" ];document.xrefPath = '");
      out.print( request.getContextPath() + Prefix.XREF_P
    );
      out.write("';document.domReady.push(function() { domReadyMenu(); });\n");
      out.write("/* ]]> */\n");
      out.write("</script>");

    }

      out.write("\n");
      out.write("<form action=\"search\" id=\"sbox\">\n");
      out.write("\n");
      out.write("<div id=\"qtbl\">\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td><label for=\"s1\" title=\"The text token(s) or other fields to be found (lucene query, this is not full text!)\">Full&nbsp;Search</label></td>\n");
      out.write("        <td><input tabindex=\"1\" class=\"q\" name=\"q\" id=\"q\" value=\"");
      out.print(
                Util.formQuoteEscape(queryParams.getFreetext()) );
      out.write("\"/></td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("\t    <td><label for=\"s2\" title=\"Definition of function/variable/class\">Definition</label></td>\n");
      out.write("        <td><input class=\"q\" tabindex=\"2\" name=\"defs\" id=\"defs\" value=\"");
      out.print(
            Util.formQuoteEscape(queryParams.getDefs()) );
      out.write("\"/></td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td><label for=\"s3\" title=\"Usage of function/variable/class\">Symbol</label></td>\n");
      out.write("        <td><input class=\"q\" tabindex=\"3\" name=\"refs\" id=\"refs\" value=\"");
      out.print(
            Util.formQuoteEscape(queryParams.getRefs()) );
      out.write("\"/></td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td><label for=\"s4\" title=\"path or parts of it, no need to use dividers\">File&nbsp;Path</label></td>\n");
      out.write("        <td><input class=\"q\" tabindex=\"4\" name=\"path\" id=\"path\" value=\"");
      out.print(
            Util.formQuoteEscape(queryParams.getPath()) );
      out.write("\"/></td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td><label for=\"s5\" title=\"Search in log messages\">History</label></td>\n");
      out.write("        <td><input class=\"q\" tabindex=\"5\" name=\"hist\" id=\"hist\" value=\"");
      out.print(
            Util.formQuoteEscape(queryParams.getHist()) );
      out.write("\"/></td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td><label for=\"s5\">Type</label></td>\n");
      out.write("        <td><select class=\"q\" tabindex=\"6\" name=\"type\" id=\"type\">");

                String selection = queryParams.getType();  
                
      out.write("\n");
      out.write("                <option value=\"\">Any</option>");

                for (Map.Entry<String, String> d : SearchHelper.getFileTypeDescirptions()) {
                    
      out.write("\n");
      out.write("                <option value=\"");
      out.print( Util.formQuoteEscape(d.getKey()) );
      out.write('"');

                    if (d.getKey().equals(selection)) {
                        
      out.write(" selected=\"selected\"");

                    }
                    
      out.write('>');
      out.print( Util.htmlize(d.getValue()) );
      out.write("</option>");

                }
            
      out.write("\n");
      out.write("            </select>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("</div>");


    if (projects.size() != 0) {

      out.write("\n");
      out.write("<div id=\"ptbl\">\n");
      out.write("    <p><label for=\"project\">In Project(s)</label> <button tabindex=\"6\" type=\"button\"\n");
      out.write("        onclick=\"javascript: selectAllProjects(); return false;\"\n");
      out.write("        >select all</button>&nbsp;<button tabindex=\"7\" type=\"button\"\n");
      out.write("        onclick=\"javascript: invertAllProjects(); return false;\"\n");
      out.write("        >invert selection</button> </p>\n");
      out.write("    <select tabindex=\"8\" ondblclick=\"javascript: goFirstProject();\"\n");
      out.write("        onkeyup=\"javascript: checkEnter(event);\" class=\"q\" id=\"project\"\n");
      out.write("        name=\"project\" multiple=\"multiple\" size=\"");
      out.print(
        Math.min(6, projects.size()) );
      out.write('"');
      out.write('>');

        SortedSet<String> pRequested = cfg.getRequestedProjects();
        for (Entry<String,String> e : pMap.entrySet()) {
            // TODO below "selected" has no effect if one refreshes the page
            // with F5 also below ondblclick doesn't work in IE ...
            // ondblclick="javascript:goFirstProject();"
        
      out.write("\n");
      out.write("        <option value=\"");
      out.print( e.getValue() );
      out.write('"');

            if (pRequested.contains(e.getKey())) {
            
      out.write(" selected=\"selected\"");

            }
            
      out.write('>');
      out.print( e.getValue() );
      out.write("</option>");

        }
        
      out.write("\n");
      out.write("    </select>\n");
      out.write("</div>");

    }
    
      out.write("\n");
      out.write("\n");
      out.write("    <p><input tabindex=\"9\" class=\"submit\" type=\"submit\" value=\"Search\"/> \n");
      out.write("    <input tabindex=\"10\" class=\"submit\" onclick=\"javascript: clearSearchFrom();\" \n");
      out.write("\t   type=\"button\" value=\" Clear \"/> \n");
      out.write("    <button class=\"help\" tabindex=\"11\" onclick=\"window.open('help.jsp');\">Help\n");
      out.write("    </button>\n");
      out.write("    </p>\n");
      out.write("</form>\n");

}
/* ---------------------- menu.jspf end --------------------- */

      out.write('\n');
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"results\">");

    // TODO spellchecking cycle below is not that great and we only create
    // suggest links for every token in query, not for a query as whole
    if (searchHelper.errorMsg != null) {
        
      out.write("<h3>Error</h3><p>");

        if (searchHelper.errorMsg.startsWith((SearchHelper.PARSE_ERROR_MSG))) {
            
      out.print( Util.htmlize(SearchHelper.PARSE_ERROR_MSG) );
      out.write("\n");
      out.write("            <br/>You might try to enclose your search term in quotes,\n");
      out.write("            <a href=\"help.jsp#escaping\">escape special characters</a>\n");
      out.write("            with <b>\\</b>, or read the <a href=\"help.jsp\">Help</a>\n");
      out.write("            on the query language. Error message from parser:<br/>\n");
      out.write("            ");
      out.print( Util.htmlize(searchHelper.errorMsg.substring(
                        SearchHelper.PARSE_ERROR_MSG.length())) );

        } else {
            
      out.print( Util.htmlize(searchHelper.errorMsg) );

        }
      out.write("</p>");

    } else if (searchHelper.hits == null) {
        
      out.write("<p>No hits</p>");

    } else if (searchHelper.hits.length == 0) {
        List<Suggestion> hints = searchHelper.getSuggestions();
        for (Suggestion hint : hints) {
        
      out.write("<p><font color=\"#cc0000\">Did you mean (for ");
      out.print( hint.name );
      out.write(")</font>:");

	  if (hint.freetext!=null) { 
	    for (String word : hint.freetext) {
            
      out.write(" <a href=\"search?q=");
      out.print( Util.URIEncode(word) );
      out.write('"');
      out.write('>');
      out.print(
                Util.htmlize(word) );
      out.write("</a> &nbsp; ");

	    }
	  }
	  if (hint.refs!=null)  {
	    for (String word : hint.refs) {
            
      out.write(" <a href=\"search?refs=");
      out.print( Util.URIEncode(word) );
      out.write('"');
      out.write('>');
      out.print(
                Util.htmlize(word) );
      out.write("</a> &nbsp; ");

	    }
	  }
	  if (hint.defs!=null) {
	    for (String word : hint.defs) {
            
      out.write(" <a href=\"search?defs=");
      out.print( Util.URIEncode(word) );
      out.write('"');
      out.write('>');
      out.print(
                Util.htmlize(word) );
      out.write("</a> &nbsp; ");

            }
	  }
        
      out.write("</p>");

        }
        
      out.write("\n");
      out.write("        <p> Your search <b>");

            Util.htmlize(searchHelper.query.toString(), out); 
      out.write("</b>\n");
      out.write("            did not match any files.\n");
      out.write("            <br/> Suggestions:<br/>\n");
      out.write("        </p>\n");
      out.write("        <ul>\n");
      out.write("            <li>Make sure all terms are spelled correctly.</li>\n");
      out.write("            <li>Try different keywords.</li>\n");
      out.write("            <li>Try more general keywords.</li>\n");
      out.write("            <li>Use 'wil*' cards if you are looking for partial match.</li>\n");
      out.write("        </ul>\n");
      out.write("        <p><b>Completed in ");
      out.print( System.currentTimeMillis() - starttime
            );
      out.write(" milliseconds</b></p>\n");
      out.write("\t");

    } else {
        // We have a lots of results to show: create a slider for
        String slider = "";
        int thispage;  // number of items to display on the current page
        int start = searchHelper.start;
        int max = searchHelper.maxItems;
        int totalHits = searchHelper.totalHits;
        if (searchHelper.maxItems < searchHelper.totalHits) {
            StringBuilder buf = new StringBuilder(4096);
            thispage = (start + max) < totalHits ? max : totalHits - start;
            StringBuilder urlp = createUrl(searchHelper, false);
            int labelStart = 1;
            int sstart = start - max * (start / max % 10 + 1) ;
            if (sstart < 0) {
                sstart = 0;
                labelStart = 1;
            } else {
                labelStart = sstart / max + 1;
            }
            int label = labelStart;
            int labelEnd = label + 11;
            for (int i = sstart; i < totalHits && label <= labelEnd; i+= max) {
                if (i <= start && start < i + max) {
                    buf.append("<span class=\"sel\">").append(label).append("</span>");
                } else {
                    buf.append("<a class=\"more\" href=\"s?n=").append(max)
                        .append("&amp;start=").append(i).append(urlp).append("\">");
                    if (label == labelStart && label != 1) {
                        buf.append("&lt;&lt");
                    } else if (label == labelEnd && i < totalHits) {
                        buf.append("&gt;&gt;");
                    } else {
                        buf.append(label);
                    }
                    buf.append("</a>");
                }
                label++;
            }
            slider = buf.toString();
        } else {
            // set the max index to max or last
            thispage = totalHits - start;
        }
        
      out.write("\n");
      out.write("        <p class=\"pagetitle\">Searched <b>");

            Util.htmlize(searchHelper.query.toString(), out);
            
      out.write("</b> (Results <b> ");
      out.print( start + 1 );
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print( thispage + start
            );
      out.write("</b> of <b>");
      out.print( totalHits );
      out.write("</b>) sorted by ");
      out.print(
            searchHelper.order.getDesc() );
      out.write("</p>");

        if (slider.length() > 0) {
        
      out.write("\n");
      out.write("        <p class=\"slider\">");
      out.print( slider );
      out.write("</p>");

        }
        
      out.write("\n");
      out.write("        <table>");

        Results.prettyPrint(out, searchHelper, start, start + thispage);
        
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <p><b>Completed in ");
      out.print( System.currentTimeMillis() - starttime
            );
      out.write(" milliseconds</b></p>");

        if (slider.length() > 0) {
        
      out.write("\n");
      out.write("        <p class=\"slider\">");
      out.print( slider );
      out.write("</p>");

        }
        
      out.write("\n");
      out.write("    </div>");

    }
    searchHelper.destroy();
}
/* ---------------------- search.jsp end --------------------- */


/* ---------------------- foot.jspf start --------------------- */
{
    cfg = PageConfig.get(request);

        
      out.write("\n");
      out.write("    <div id=\"footer\">\n");
      out.write("<p><a href=\"http://opengrok.github.com/OpenGrok/\"\n");
      out.write(" title=\"Served by OpenGrok\"><span id=\"fti\"></span></a></p>\n");
      out.write("<p>Indexes created ");
      out.print( cfg.getEnv().getDateForLastIndexRun() );
      out.write("</p>\n");
      out.write("    ");
      out.print( cfg.getEnv().getConfiguration().getFooterIncludeFileContent() );
      out.write("\n");
      out.write("    ");

    if (needAddDiv.contains(cfg.getPrefix())) {
        
      out.write("</div>");
 // #content
    }
    // #footer, #page:
    
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");

}
/* ---------------------- foot.jspf end --------------------- */

      out.write('\n');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
