import java.awt.Point;
import java.awt.Rectangle;

public class ButtonSpacePartitionNode {
    private ButtonSpacePartitionNode NW;
    private ButtonSpacePartitionNode NE;
    private ButtonSpacePartitionNode SW;
    private ButtonSpacePartitionNode SE;
    private LinkedList<Button> value;

    private Rectangle NWRectangle(Rectangle r){
        return new Rectangle(r.x, r.y, r.width / 2, r.height / 2);
    }

    private Rectangle NERectangle(Rectangle r){
        return new Rectangle(r.x + r.width / 2, r.y, r.width / 2, r.height / 2);
    }

    private Rectangle SWRectangle(Rectangle r){
        return new Rectangle(r.x, r.y + r.height / 2, r.width / 2, r.height / 2);
    }

    private Rectangle SERectangle(Rectangle r){
        return new Rectangle(r.x + r.width / 2, r.y + r.height / 2, r.width / 2, r.height / 2);
    }

    public LinkedList<Button> search(Point p, Rectangle border, Rectangle r, int remainingResolution){
        LinkedList<Button> toReturnList = null;

        if (remainingResolution == 0){
            toReturnList = new LinkedList<>();
            Button b;
            while (value.hasNext()) {
                b = value.next();
                if (b.isOver(p, border)){
                    toReturnList.insert(b);
                    
                }
            }
            value.restart();
        }

        else{
            Point center = new Point(r.x + r.width / 2, r.y + r.height / 2);

            if (p.x < center.x && p.y < center.y && NW != null){
                toReturnList = NW.search(p, border, NWRectangle(r), remainingResolution - 1);
            }
            else if (p.y < center.y && NE != null){
                toReturnList = NE.search(p, border, NERectangle(r), remainingResolution - 1);
            }
            else if (p.x < center.x && SW != null){
                toReturnList = SW.search(p, border, SWRectangle(r), remainingResolution - 1);
            }
            else if (SE != null){
                toReturnList = SE.search(p, border, SERectangle(r), remainingResolution - 1);
            }
            else{
                toReturnList = new LinkedList<>();
            }

            if (value != null){
                toReturnList.insertValues(value);
            }
        }

        return toReturnList;
    }

    public void insert(Button b, Rectangle hitBox, Rectangle r, int remainingResolution){
        if (remainingResolution == 0 || hitBox.contains(r)){
            if (value == null){
                value = new LinkedList<>();
            }
            value.insert(b);
        }
        else{
            Point center = new Point(r.x + r.width / 2, r.y + r.height / 2);
            boolean isLeft = hitBox.x < center.x;
            boolean isRight = hitBox.x + hitBox.width >= center.x;
            boolean isDown = hitBox.y < center.y;
            boolean isUp = hitBox.y + hitBox.height >= center.y;
            Rectangle newR;
            if (isLeft && isDown){
                newR = NWRectangle(r);
                if (newR.intersects(hitBox)){
                    if (NW == null){
                        NW = new ButtonSpacePartitionNode();
                    }
                    NW.insert(b, hitBox, newR, remainingResolution - 1);
                }
            }
            if (isRight && isDown){
                newR = NERectangle(r);
                if (newR.intersects(hitBox)){
                    if (NE == null){
                        NE = new ButtonSpacePartitionNode();
                    }
                    NE.insert(b, hitBox, newR, remainingResolution - 1);
                }
            }
            if (isLeft && isUp){
                newR = SWRectangle(r);
                if (newR.intersects(hitBox)){
                    if (SW == null){
                        SW = new ButtonSpacePartitionNode();
                    }
                    SW.insert(b, hitBox, newR, remainingResolution - 1);
                }
            }
            if (isRight && isUp){
                newR = SERectangle(r);
                if (newR.intersects(hitBox)){
                    if (SE == null){
                        SE = new ButtonSpacePartitionNode();
                    }
                    SE.insert(b, hitBox, newR, remainingResolution - 1);
                }
            }
        }
    }
}
