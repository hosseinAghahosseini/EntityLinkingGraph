# EntityLinkingGraph
Imagine a case that you want to know about something, what would you do? A typical solution would be looking for its Wikipedia page.
But what if that page is related to many other pages and you should know about them first to achieve your desired outcome.
Or maybe you want to know that what subjects are dependent to a specified subject and maybe you want to display this dependency in something like Graph for example.

In these cases, EntityLinkingGraph may help you.
EntityLinkingGraph will process a Wikipedia title and find Wikipedia pages that are related to that title in a recursive way, and for ease of reading, it shows these pages in a Graph. 
Also it uses topological sort on the graph in order to make a list which shows what wiki pages you should read first, in order to fully understand a wiki title.

Our Application uses two methods: Online and Offline.
In Offline mode, we use a dataset that contains Wikipedia page name entities and we build the dependency graph based on that dataset.
In Online mode, we dynamically read through a wiki page, find its Links to other wiki pages and build the graph on these links.

The root page of project is the directory of the online method project and /EntityLiningGraph page is the offline methods directory.

Note that if you import project to NetBeans, Online mode would be the main and if you import it to eclipse, offline mode would be so!

For Example this image shows the dependency Graph of the wiki Page of actor Shahab Hosseini which is made by EntityLinkingGraph:
http://s9.picofile.com/file/8328495368/shahabHosseiniGraph.jpg
