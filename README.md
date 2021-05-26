[![OmegaUna Logo](https://ou.omegauna.eu/OmegaUnaIcon.png)](https://ou.omegauna.eu)
   
[![CircleCI Build Status](https://circleci.com/gh/electron/electron/tree/master.svg?style=shield)](https://circleci.com/gh/electron/electron/tree/master)
[![AppVeyor Build Status](https://ci.appveyor.com/api/projects/status/4lggi9dpjc1qob7k/branch/master?svg=true)](https://ci.appveyor.com/project/electron-bot/electron-ljo26/branch/master)
[![devDependency Status](https://david-dm.org/electron/electron/dev-status.svg)](https://david-dm.org/electron/electron?type=dev)
[![Electron Discord Invite](https://img.shields.io/discord/745037351163527189?color=%237289DA&label=chat&logo=discord&logoColor=white)](https://discord.com/invite/electron)


This project is maintained by one of the students at UCLL Campus Proximus.
The goal is to learn MVC, a popular and important software design pattern.
Contribute to this project by writing HTML, CSS, Javascript and java. Deployment is done on Tomcat server, the school uses Cyclone.

By participating, you are expected to uphold this code. Please report unacceptable
behavior to [admin@omegauna.eu](mailto:admin@omegauna.eu).
## Deployment
There is a **conflict** with the asset file paths between the **Local** and **Remote Server.**
To battle this, I created a **seperate branch** called "Remote Server" which contains the right code to deploy on **Cyclone**.
The main branch contains Local Server code.
## Development

Development was done with [IntelliJ from JetBrains](https://www.jetbrains.com/idea/) and [Tomcat Server 9.0.44](https://tomcat.apache.org/download-90.cgi).

## Programmatic usage
### Preperation of Controller
Most people would use Factories and/or Seeders, but if you require more advanced code you should wait for release `v2.0`.
Use this to spawn multiple Server objects:
```java
/* init() function | Controller */
public void init() throws ServletException {
    super.init();
    try {
        //new ServerFactory(db, 10);
        new ServerDBSeeder(db);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### Routing
```java
/* requestMiddleware() function | Controller */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    requestMiddleware(request,response);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    requestMiddleware(request,response);
}
private void requestMiddleware(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(db); // Make sure the runtime Database object is not null
        System.out.println(db.getServers()); // Make sure the runtime Database data is not null or empty

        String route = ( request.getParameter("route") != null ) ? request.getParameter("route") : "home";
        System.out.println("ROUTED: " + route);
        String destination;
        switch (route){
            case "home":
                destination = home(request, response);
                break;
            case "create":
                destination = create(request, response);
                break;
            case "read":
                destination = read(request,response);
                break;
            case "update":
                destination = update(request,response);
                break;
            case "delete":
                destination = delete(request,response);
                break;
            default :
                destination = error_404(request,response);
        }
        request.getRequestDispatcher(destination).forward(request,response);
    }
```
### View
Use this to spawn Server objects from the runtime Database into a View:
```java
/* web-page.jsp | View */

ServerDB db = (ServerDB) request.getAttribute("db");
ArrayList<Server> servers = db.getServers();

   
```
## Advanced usage

The remote server is accesible to multiple developers. This means runtime data can be altered.
I created an Admin page where you can **reload** the server; and restore the runtime default data.
Location ``Controller?route=admin-reload-server``.

### Reloading server in runtime
```java
/* "admin-reload-server" Route in the (Admin)Controller */

private String admin_reloadServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setAttribute("amountOfServers", db.getServers().size());
    request.setAttribute("amountOfMBInUse", db.getMBInUse());
    if (request.getParameter("command") != null && request.getParameter("command").equals("reload-server")) {
        db.reload();
        request.setAttribute("status", "Data has been refreshed!");
        request.setAttribute("amountOfServers", db.getServers().size());
        request.setAttribute("amountOfMBInUse", db.getMBInUse());
        return "admin/reload-server.jsp";
    }
    return "admin/reload-server.jsp";
}

   
```

## Contributing

If you are interested in reporting/fixing issues and contributing directly to the code base, please see [CONTRIBUTING.md](CONTRIBUTING.md) for more information on what we're looking for and how to get started.

## Community

Info on reporting bugs, getting help, finding third-party tools and sample apps,
and more can be found by emailing **dazzy.edu@gmail.com**.
