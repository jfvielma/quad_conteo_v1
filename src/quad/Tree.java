package quad;
/*
* "Tree.java" - Demonstrates various types of minimum-length
* interconnection trees (minimum spanning tree, Steiner tree, etc.) and
* maximum-length interconnection trees.
* The tree is used as example for several examples of spatial indexes:
*  # Simple grid.
*  # PR K-d tree.
*  # PR Quadtree.
*  # Point Quadtree.
*  # K-d Tree.
*  # Simple edge grid 
*  # MX Quadtree.
*  # PMR Quadtree.
* And by another methods of line drawing: Maximum spanning tree.
* 
* Exteranal parameters: MaxNumOfPoints - internal "maxN" (value 2-40)
*                       InitialNumOfPoints - internal "n" (value 2-maxN)
*                       Radius - nodes representation radius. (value 3-10)
*
* based on a Spanning tree program by : Joe Ganley, ganley@cadence.com
* Authors:     Muky Haklay, mukyh@cs.huji.ac.il
*              Sabina Gertz, sabina@cs.huji.ac.il
* 
* This code was updated. See http://www.casa.ucl.ac.uk/muki/java.htm
*/


import java.lang.*;
import java.awt.*;

public class Tree extends java.applet.Applet {

private int         maxN;           // the max number of terminals
private int         n;              // the current number of terminals
private int         QuadVal = 1;    // the index type 
private int         TreeVal = 1;    // the tree type
private int         PaintW;         // Paint area width
private int         PaintH;         // Paint area height
private int         pt_center;      // the number of the first pointd 
private int         r;              // the radius of a terminal
private Point       p[];            // the terminals
private Point       l[];            // the lines
private Point       current;        // the current one and its old location
private boolean     m[][];          // the minimum spanning tree edges
private Rectangle   border, inner;  // applet borders
private Scrollbar   NodesSB;        // scrollbar for changing n
private Label       NodesLBL;       // text field for changing n
private Choice      QmethodChoice;  // the method that the user select
private Choice      TreeChoice;     // the tree that the user select
private Button      AboutButton;    // the "about" button
private Image       buffer;         // buffer for double-buffering
private Graphics    bufg;           // buffer's Graphics

/***********************************************************************
   Function name: init
   Description:   initializitation of the applet, and global variables.
***********************************************************************/
public void init() {
  String ParamString;

  // Read external parameters
  ParamString = getParameter("MaxNumOfPoints");
  if (ParamString != null) {
     try {
         maxN = Integer.valueOf(String.valueOf(ParamString)).intValue();
     } 
     catch (java.lang.NumberFormatException e) {
         maxN = 20;
     }
  } else {
     maxN = 20;
  }
  if (maxN > 40) {
     maxN = 40;
  }
  if (maxN < 2) {
     maxN = 2;
  }

  ParamString = getParameter("InitialNumOfPoints");
  if (ParamString != null) {
     try {
         n = Integer.valueOf(String.valueOf(ParamString)).intValue();
     } 
     catch (java.lang.NumberFormatException e) {
         n = 10;
     }
  } else {
     n = 10;
  }
  if (n > maxN) {
     n = maxN;
  }
  if (n < 2) {
     n = 2;
  }

  ParamString = getParameter("Radius");
  if (ParamString != null) {
     try {
         r = Integer.valueOf(String.valueOf(ParamString)).intValue();
     } 
     catch (java.lang.NumberFormatException e) {
         r = 4;
     }
  } else {
    r = 4;
  }
  if (r > 10) {
     r = 10;
  }
  if (r < 3) {
     r = 3;
  }

  p = new Point[maxN];
  l = new Point[maxN];
  current = null;
  m = new boolean[maxN][maxN];
  setLayout(new BorderLayout());
  setBackground(Color.white);

  Panel controlPanel = new Panel();
  controlPanel.setLayout(new FlowLayout());
  controlPanel.setBackground(Color.gray);

  Label TreeLabel = new Label("Tree type:", Label.CENTER);
  TreeChoice = new Choice();
  TreeChoice.addItem(" Min. Span ");
  TreeChoice.addItem(" Max. Span ");

  Label QmethodLabel = new Label("Method:", Label.CENTER);
  QmethodChoice = new Choice();
  QmethodChoice.addItem(" Simple Grid ");
  QmethodChoice.addItem(" PR K-d Tree ");
  QmethodChoice.addItem(" PR Quadtree ");
  QmethodChoice.addItem(" Point Quadtree ");
  QmethodChoice.addItem(" K-d Tree ");
  QmethodChoice.addItem(" Simple Edges Grid ");
  QmethodChoice.addItem(" MX Quadtree ");
  QmethodChoice.addItem(" PMR Quadtree ");

  Label  NodesLabel = new Label("Number of nodes:", Label.CENTER);
  NodesLBL = new Label(String.valueOf(n));
  NodesSB = new Scrollbar(Scrollbar.HORIZONTAL, n, 1, 2, maxN);
  AboutButton = new Button("About");

  controlPanel.add(TreeLabel);
  controlPanel.add(TreeChoice);
  controlPanel.add(QmethodLabel);
  controlPanel.add(QmethodChoice);
  controlPanel.add(NodesLabel);
  controlPanel.add(NodesLBL);
  controlPanel.add(NodesSB);
  controlPanel.add(AboutButton);
  add("South",controlPanel);

  border = new Rectangle(0, 0, size().width - 1, size().height - 40);
  inner =  new Rectangle(r + 1, r + 1, 
               size().width - 2 * r - 3, size().height - 2 * r - 43);

  PaintW = size().width - 1;
  PaintH = size().height - 40;

  // initialize the terminals to random locations
  for (int i = 0; i < maxN; i++) {
    p[i] = new Point((int) Math.round(Math.random()
                     * (size().width - 2 * r - 2) + r + 1),
                     (int) Math.round(Math.random()
                    * (size().height - 60 - 2 * r - 2) + r + 1));
    l[i] = new Point(0,0);
    for (int j = 0; j < maxN; j++) {
      m[i][j] = false;
    }
  }
  minst();

  buffer = createImage(size().width, size().height-40);
  bufg = buffer.getGraphics();
  bufg.setFont(getFont());
} // init()


/***********************************************************************
   Function name: update
   Description:   updates the graphic display of nodes, links and
                  the represantation of the current spatial index.
***********************************************************************/
public void update(Graphics g) {
  bufg.setColor(getBackground());
  bufg.fillRect(border.x, border.y, border.width, border.height);
  bufg.setColor(Color.black);
  bufg.drawRect(border.x, border.y, border.width, border.height);

  // first do the MST edges
  bufg.setColor(Color.red);
  for (int i = 0; i < n; i++) {
    for (int j = (i + 1); j < n; j++) {
      if (m[i][j]) {
         bufg.drawLine(p[i].x, p[i].y, p[j].x, p[j].y);
      }
    }
  }
  
  // redraw the terminals
  for (int i = 0; i < n; i++) {
    bufg.setColor(Color.green);
    bufg.fillOval(p[i].x - r, p[i].y - r, 2 * r, 2 * r);
    bufg.setColor(Color.black);
    bufg.drawOval(p[i].x - r, p[i].y - r, 2 * r, 2 * r);
  }

  // draw the current one in cyan
  if (current != null) {
    bufg.setColor(Color.cyan);
    bufg.fillOval(current.x - r, current.y - r, 2 * r, 2 * r);
    bufg.setColor(Color.black);
    bufg.drawOval(current.x - r, current.y - r, 2 * r, 2 * r);
  } 

  // draw the current represantaion of the spatial index
  switch (QuadVal) {
     case 1: {
        bufg.setColor(Color.black);
        SimpleGrid(0,0,PaintW,PaintH);
     }    
     break;
     case 2: {
        bufg.setColor(Color.magenta);
        PRKDtree(0,0,PaintW,PaintH,1);
     }
     break;
     case 3: {
        bufg.setColor(Color.orange);
        PRQuadtree(0,0,PaintW,PaintH);
     }
     break;
     case 4: {
        bufg.setColor(Color.pink);
        PointQuadtree(0,0,PaintW,PaintH);
     }
     break;
     case 5: {
        bufg.setColor(Color.darkGray);
        KDTree(0,0,PaintW,PaintH,1);
     }
     break;
     case 6: {
        bufg.setColor(Color.darkGray);
        SimpleEdgesGrid(0,0,PaintW,PaintH);
     }
     break;
     case 7: {
        bufg.setColor(Color.gray);
        MXQuadtree(0,0,PaintW,PaintH);
     }
     break;
     case 8: {
        bufg.setColor(Color.gray);
        PMRQuadtree(0,0,PaintW,PaintH);
     }
     break;
     default: {
       break;
     }
  } // switch
 
  g.drawImage(buffer, 0, 0, null);
} // update(Graphics)

/***********************************************************************
   Function name: paint
   Description:   draw the canvas again.
***********************************************************************/
public void paint(Graphics g) {
  update(g);
} // paint(Graphics)


/***********************************************************************
   Function name: handleEvent
   Description:   event handling function. The events are:
      MOUSE_*  - moving a node.
      SCROLL_* - changing the number of nodes.
      ACTION   - the other action is if the user changed 
                 the  selected method.
***********************************************************************/
public boolean handleEvent(Event evt) {
  switch (evt.id) {

  case Event.MOUSE_DOWN: {
    Rectangle rect = new Rectangle();

    current = null;
    for (int i = 0; (i < n) && (current == null); i++) {
      rect.reshape(p[i].x - r, p[i].y - r, 2 * r, 2 * r);
      if (rect.inside(evt.x, evt.y)) {
      current = p[i];
      }
    }
    break;
  }

  case Event.MOUSE_UP: {
    current = null;
    repaint();
    break;
  }

  case Event.MOUSE_DRAG: {
    if (current != null) {
      if (inner.inside(evt.x, evt.y)) {
         current.move(evt.x, evt.y);
      }
      else {
        current.move(Math.max(Math.min(evt.x, inner.x + inner.width), inner.x),
           Math.max(Math.min(evt.y, inner.y + inner.height), inner.y));
      }
      switch (TreeVal){
        case 1: {
           minst();
        }
        break;
        case 2: {
           maxst();
        }
        break;
      }
      repaint();
    }
    break;
  }

  case Event.SCROLL_LINE_UP: case Event.SCROLL_LINE_DOWN:
  case Event.SCROLL_PAGE_UP: case Event.SCROLL_PAGE_DOWN:
  case Event.SCROLL_ABSOLUTE: {
    n = NodesSB.getValue();
    NodesLBL.setText(String.valueOf(NodesSB.getValue()));
    switch (TreeVal){
      case 1: {
        minst();
      }
      break;
      case 2: {
        maxst();
      }
      break;
    }
    repaint();
    break; 

  }

  case Event.ACTION_EVENT: {
    return (action (evt, evt.arg));
  }

  default: {
    break;
  }

  } // switch

  return(true);
} // handleEvent(Event)

/***********************************************************************
   Function name: action
   Description:   event handler for the choice list.
***********************************************************************/
public boolean action(Event evt,Object arg)
{
  String label=(String)arg;

  if (evt.target instanceof Choice) {
    if (label.equals(" Min. Span ")) {
        TreeVal = 1;
        minst();
        repaint();
        return(true);
    }
    if (label.equals(" Max. Span ")) {
        TreeVal = 2;
        maxst();
        repaint();
        return(true);
    }
    if (label.equals(" Simple Grid ")) {
        QuadVal = 1;
        repaint();
        return(true);
    }
    if (label.equals(" PR K-d Tree ")) {
        QuadVal = 2;
        repaint();
        return(true);
    }
    if (label.equals(" PR Quadtree ")) {
        QuadVal = 3;
        repaint();
        return(true);
    }
    if (label.equals(" Point Quadtree ")) {
       QuadVal = 4;
       repaint();
       return(true);
    }
    if (label.equals(" K-d Tree ")) {
       QuadVal = 5;
       repaint();
       return(true);
    }
    if (label.equals(" Simple Edges Grid ")) {
       QuadVal = 6;
       repaint();
       return(true);
    }
    if (label.equals(" MX Quadtree ")) {
       QuadVal = 7;
       repaint();
       return(true);
    }
    if (label.equals(" PMR Quadtree ")) {
       QuadVal = 8;
       repaint();
       return(true);
    }
  }
  if (evt.target instanceof Button) {
    if ( label.equals("About")) {
       AboutFrame about = new AboutFrame () ;
       about.show () ;
    }
  }
  return(true);
}

/***********************************************************************
   Function name: distance
   Description:   Euclidean distance between two points (x1,y1) and (x2,y2)
***********************************************************************/
private int distance(int x1, int y1, int x2, int y2) {
  return((int) Math.round(Math.sqrt(
     (double) (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))));
} // distance(int,int,int,int)


/***********************************************************************
   Function name: minst 
   Description:   compute a minimum spanning tree using Prim's algorithm 
                  with "dumb" heaps.  This is the fastest graph algorithm 
                  for complete graphs, though we could do better 
                  geometrically - but with 10 terminals, why bother?
***********************************************************************/
private void minst() {
  int dist[], neigh[], closest, minDist, d, k;

  dist = new int[n];
  neigh = new int[n];

  // initialize data structures
  for (int i = 0; i < n; i++) {
    dist[i] = distance(p[0].x, p[0].y, p[i].x, p[i].y);
    neigh[i] = 0;
    for (int j = 0; j < n; j++) {
      m[i][j] = false;
    }
  }

  // find terminal closest to current partial tree
  for (int i = 1; i < n; i++) {
    closest = -1;
    minDist = Integer.MAX_VALUE;
    for (int j = 1; j < n; j++) {
      if ((dist[j] != 0) && (dist[j] < minDist)) {
        closest = j;
        minDist = dist[j];
      }
    }
    
    // set an edge from it to its nearest neighbor
    m[neigh[closest]][closest] = true;
    m[closest][neigh[closest]] = true;

    // update nearest distances to current partial tree
    for (int j = 1; j < n; j++) {
      d = distance(p[j].x, p[j].y, p[closest].x, p[closest].y);
      if (d < dist[j]) {
        dist[j] = d;
        neigh[j] = closest;
      }
    }
  }

  // update the edges arrey
  k = 0;
  for (int i = 0; i < n; i++) {
    for (int j = (i + 1) ; j < n; j++) {
      if (m[i][j]) {
        l[k].x = i; 
        l[k].y = j;
        k++;
      }
    }
  }
} // minst()

/***********************************************************************
   Function name: maxst 
   Description:   compute a maximum spanning tree using Prim's algorithm 
                  with "dumb" heaps.  This is the fastest graph algorithm 
                  for complete graphs, though we could do better 
                  geometrically - but with 10 terminals, why bother?
***********************************************************************/
private void maxst() {
  int dist[], neigh[], farest, maxDist, d, k;

  dist = new int[n];
  neigh = new int[n];

  // initialize data structures
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      m[i][j] = false;
    }
  }

  // find terminal fareest to current partial tree
  for (int i = 0; i < n; i++) {
    farest = -1;
    maxDist = Integer.MIN_VALUE;
    for (int j = 0; j < n; j++) {
      d = distance(p[i].x,p[i].y,p[j].x,p[j].y);
      if ((d > maxDist) && (i != j)) {
        farest = j;
        maxDist = d;
      }
    }
    
    // set an edge from it to its distant neighbor
    m[i][farest] = true;
    m[farest][i] = true;
  }

  // update the edges arrey
  k = 0;
  for (int i = 0; i < n ; i++) {
    for (int j = i + 1; j < n ; j++) {
      if (m[i][j]) {
        l[k].x = i; 
        l[k].y = j;
        k++;
      }
    }
  }
} // maxst()

/***********************************************************************
   Function name: SimpleGrid
   Description:   a recursive function, that creates a simple grid
                  on the area between (x1,y1) and (x2,y2).
                  The grid is drawn by splicing the area into 4 
                  squares - and running SimpleGrid on them.
***********************************************************************/
private void SimpleGrid(int x1, int y1, int x2, int y2) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (((x2-x1)/2 > (r * 1)) && ((y2-y1)/2 > (r * 1))) { 
    bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
    bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
    SimpleGrid(x1,y1,x1+Dx/2,y1+Dy/2);
    SimpleGrid(x1,y1+Dy/2,x1+Dx/2,y2);
    SimpleGrid(x1+Dx/2,y1,x2,y1+Dy/2);
    SimpleGrid(x1+Dx/2,y1+Dy/2,x2,y2);
  }
}

/***********************************************************************
   Function name: PRKDtree
   Description:   a recursive function, that creates a PR K-d tree
                  on the area between (x1,y1) and (x2,y2).	The parameter
                  "Stat" is used to know which alignment is the current
                  alignment.
***********************************************************************/
private void PRKDtree(int x1, int y1, int x2, int y2,int Stat) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (PointInArea(x1,y1,x2,y2)) {
     if (Stat == 1) {
        bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
        if ((x2-x1)/2 > (r * 3)) { 
          PRKDtree(x1+Dx/2,y1,x1+Dx,y2,0);
          PRKDtree(x1,y1,x1+Dx/2,y2,0);
        }
     } else {
        bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
        if ((y2-y1)/2 > (r * 3)) { 
          PRKDtree(x1,y1,x2,y1+Dy/2,1);
          PRKDtree(x1,y1+Dy/2,x2,y1+Dy,1);
        }
     }
  }
}

/***********************************************************************
   Function name: PRQuadtree
   Description:   a recursive function, that creates a PR Quadtree
                  on the area between (x1,y1) and (x2,y2).
***********************************************************************/
private void PRQuadtree(int x1, int y1, int x2, int y2) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (PointInArea(x1,y1,x2,y2) && ((x2-x1)/2 > (r * 1)) 
       && ((y2-y1)/2 > (r * 1))) { 
    bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
    bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
    PRQuadtree(x1,y1,x1+Dx/2,y1+Dy/2);
    PRQuadtree(x1,y1+Dy/2,x1+Dx/2,y2);
    PRQuadtree(x1+Dx/2,y1,x2,y1+Dy/2);
    PRQuadtree(x1+Dx/2,y1+Dy/2,x2,y2);
  }
}

/***********************************************************************
   Function name: PointQuadtree
   Description:   a recursive function, that creates a Point Quadtree
                  on the area between (x1,y1) and (x2,y2).
***********************************************************************/
private void PointQuadtree(int x1, int y1, int x2, int y2) {
  int Cx, Cy;

  if (PointInArea(x1,y1,x2,y2)) {
     Cx = p[pt_center].x;
     Cy = p[pt_center].y;
     bufg.drawLine(Cx,y1,Cx,y2);
     bufg.drawLine(x1,Cy,x2,Cy);
     PointQuadtree(x1,y1,Cx-1,Cy-1);
     PointQuadtree(Cx+1,Cy+1,x2,y2);
     PointQuadtree(x1,Cy+1,Cx-1,y2);
     PointQuadtree(Cx+1,y1,x2,Cy-1);
  }
}

/***********************************************************************
   Function name: KDTree
   Description:   a recursive function, that creates a KD tree
                  on the area between (x1,y1) and (x2,y2).	The parameter
                  "Stat" is used to know which alignment is the current
                  alignment.
***********************************************************************/
private void KDTree(int x1, int y1, int x2, int y2, int Stat) {
  int Cx, Cy;

  if (PointInArea(x1,y1,x2,y2)) {
     Cx = p[pt_center].x;
     Cy = p[pt_center].y;
     if (Stat == 1) {
        bufg.drawLine(Cx,y1,Cx,y2);
        KDTree(x1,y1,Cx-1,y2,0);
        KDTree(Cx+1,y1,x2,y2,0);
     } else {
        bufg.drawLine(x1,Cy,x2,Cy);
        KDTree(x1,y1,x2,Cy-1,1);
        KDTree(x1,Cy+1,x2,y2,1);
     }
  }
}

/***********************************************************************
   Function name: SimpleEdgesGrid
   Description:   a recursive function, that creates a simple edges
                  on the area between (x1,y1) and (x2,y2).
                  The output: every grid cell that is crosed by 
                  an edge, is marked with a cross.
***********************************************************************/
private void SimpleEdgesGrid(int x1, int y1, int x2, int y2) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (((x2-x1)/2 > (r * 3)) && ((y2-y1)/2 > (r * 3))) { 
    bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
    bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
    SimpleEdgesGrid(x1,y1,x1+Dx/2,y1+Dy/2);
    SimpleEdgesGrid(x1,y1+Dy/2,x1+Dx/2,y2);
    SimpleEdgesGrid(x1+Dx/2,y1,x2,y1+Dy/2);
    SimpleEdgesGrid(x1+Dx/2,y1+Dy/2,x2,y2);
  } else {
    if (IntersectCount(x1,y1,x2,y2)>0) {
       bufg.drawLine(x1, y2, x2, y1);
       bufg.drawLine(x1, y1, x2, y2);
    }
  } 
}

/***********************************************************************
   Function name: MXQuadtree
   Description:   a recursive function, that creates a an MX Quadtree
                  on the area between (x1,y1) and (x2,y2).
                  The output: every grid cell that is crosed by 
                  an edge, is marked with a cross.
***********************************************************************/
private void MXQuadtree(int x1, int y1, int x2, int y2) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (((x2-x1)/2 > (r * 3)) && ((y2-y1)/2 > (r * 3))) { 
     if (IntersectCount(x1,y1,x2,y2)>0) {
         bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
         bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
         MXQuadtree(x1,y1,x1+Dx/2,y1+Dy/2);
         MXQuadtree(x1,y1+Dy/2,x1+Dx/2,y2);
         MXQuadtree(x1+Dx/2,y1,x2,y1+Dy/2);
         MXQuadtree(x1+Dx/2,y1+Dy/2,x2,y2);
     }
  } else {
    if (IntersectCount(x1,y1,x2,y2)>0) {
       bufg.drawLine(x1, y2, x2, y1);
       bufg.drawLine(x1, y1, x2, y2);
    }
  }
}

/***********************************************************************
   Function name: PMRQuadtree
   Description:   a recursive function, that creates a an PMR Quadtree
                  on the area between (x1,y1) and (x2,y2).
***********************************************************************/
private void PMRQuadtree(int x1, int y1, int x2, int y2) {
  int Dx = x2-x1;
  int Dy = y2-y1;

  if (((x2-x1)/2 > (r * 3)) && ((y2-y1)/2 > (r * 3))) { 
     if (IntersectCount(x1,y1,x2,y2)>2) {
         bufg.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
         bufg.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
         PMRQuadtree(x1,y1,x1+Dx/2,y1+Dy/2);
         PMRQuadtree(x1,y1+Dy/2,x1+Dx/2,y2);
         PMRQuadtree(x1+Dx/2,y1,x2,y1+Dy/2);
         PMRQuadtree(x1+Dx/2,y1+Dy/2,x2,y2);
     }
  } 
}

/***********************************************************************
   Function name: PointInArea
   Description:   this function finds out if one of the points between
                  0 and "n" (the current number of nodes) is inside the
                  area between (x1,y1) and (x2,y2).
                  The function returns "true" if there are any points.
***********************************************************************/
private boolean PointInArea(int x1, int y1, int x2, int y2) {
  int count = 0;
  pt_center = 999; // The first point that was found.
  for (int j = 0; j < n; j++) {
    if (p[j].x >= x1 && p[j].x <= x2 && p[j].y >= y1 && p[j].y <= y2) {
       if (pt_center == 999) {
          pt_center = j;
       }
       count++;
    }
  }
  if (count < 2) {
    return(false);
  } else {
    return(true);
  }
}

/***********************************************************************
   Function name: IntersetCount
   Description:   this function finds out how many segments are
                  crossing the area between (x1,y1) and (x2,y2).
***********************************************************************/
private int IntersectCount(int x1, int y1, int x2, int y2) {
   int i;
   int counter = 0;

   for (i=0;i< n-1 ;i++) {
     if (SegmentInArea(x1,y1,x2,y2,p[l[i].x].x,p[l[i].x].y,
        p[l[i].y].x,p[l[i].y].y)) {
       counter++;
     }
   }
   return (counter);
}

/***********************************************************************
   Function name: SegmentInArea
   Description:   this function finds out if the segment (x3,y3,x4,y4) 
                  crosses the area between (x1,y1) and (x2,y2).
***********************************************************************/
private boolean SegmentInArea(int x1, int y1, int x2, int y2,
                              int x3, int y3, int x4, int y4) {
   float fx1,fy1,fx2,fy2,fx3,fy3,fx4,fy4; // Float container for extant
   float MinX,MinY,MaxX,MaxY;             // Minimum and maximum of the segment
   float a1,b1,a2,b2,a3,b3;               // Equation parameters of the lines 
   float intersectX,intersectY;           // Intersection point

   // First - transformation of the int val's to float.
   fx1 = x1;
   fy1 = y1;
   fx2 = x2;
   fy2 = y2;
   fx3 = x3;
   fy3 = y3;
   fx4 = x4;
   fy4 = y4;
   
   MinX = Math.min(fx3,fx4);
   MaxX = Math.max(fx3,fx4);
   MinY = Math.min(fy3,fy4);
   MaxY = Math.max(fy3,fy4);
   
   a1 = (fy2-fy1)/(fx2-fx1);
   b1 = -(fy2-fy1)/(fx2-fx1)*fx1 + fy1;
   a2 = (fy1-fy2)/(fx2-fx1);
   b2 = -(fy1-fy2)/(fx2-fx1)*fx1 + fy2;
   a3 = (fy4-fy3)/(fx4-fx3);
   b3 = (fy4-fy3)/(fx4-fx3)*(-fx3)+fy3;

   intersectX = -((b1-b3)/(a1-a3));
   intersectY = (a1*b3-a3*b1)/(a1-a3);

   if ((fx1<=intersectX) && (fx2>=intersectX) && (fy1<=intersectY) &&
       (fy2>=intersectY) && (fx1 < MaxX) && (fy1 < MaxY) && (fx2 > MinX) && 
       (fy2 > MinY)) {
       return (true); 
   }
   intersectX = -((b2-b3)/(a2-a3));
   intersectY = (a2*b3-a3*b2)/(a2-a3);
   if ((fx1<=intersectX) && (fx2>=intersectX) && (fy1<=intersectY) && 
       (fy2>=intersectY) && (fx1 < MaxX) && (fy1 < MaxY) && (fx2 > MinX) &&
       (fy2 > MinY)) { 
       return (true); 
   }
   return (false);
}


} // class Tree extends java.applet.Applet

class AboutFrame extends Frame {

  Button  OK = new Button ("OK") ;
  Label Caption = new Label ("Spatial indexing demo V1.0", Label.CENTER) ;
  Label Space   = new Label ("************************", Label.CENTER) ;
  Label Author  = new Label
                ("(C) Sabina Gertz & Muky Haklay, 1996", Label.CENTER) ;
  Label Author1  = new Label 
               ("Based on spanning tree applet by Joe Ganley", Label.LEFT) ;
  Label Space1   = new Label ("************************", Label.CENTER) ;
  Label Help  = new Label 
               ("# Select type of Index and tree type.", Label.LEFT) ;
  Label Help1 = new Label 
               ("# Use mouse to move nodes around.",  Label.LEFT) ;
  Label Help2 = new Label 
               ("# Use slider to change the number of nodes.",  Label.LEFT) ;

  public AboutFrame () {
      super ("Spatial indexing demo") ;
      resize (300, 280) ;
      setResizable (false) ;
      setLayout (new GridLayout (9, 0, 0, 3)) ;

      add (Caption) ;
      add (Space) ;
      add (Author) ;
      add (Author1) ;
      add (Space1) ;
      add (Help) ;
      add (Help1) ;
      add (Help2) ;
      add (OK) ;
  }

  public synchronized boolean handleEvent (Event event) {
      if (event.id != Event.ACTION_EVENT)
          return false ;
      
      if ("OK".equals (event.arg))
          dispose () ;
      return true ;
  }
} // class AboutFrame extends Frame 

