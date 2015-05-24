<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>JSP Page</title>
  </head>
  <body>
    <p>
      Server info: <%= application.getServerInfo()%><br/>  
      Servlet version: <%= application.getMajorVersion()%>.<%= application.getMinorVersion()%><br/>  
      JSP version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion()%>
    </p>
  </body>
</html>
