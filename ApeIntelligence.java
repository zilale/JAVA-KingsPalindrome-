
/**
 * KingsPalindrome
 * @author Žilvinas Aleksa
 * @ID 1703749
 */

import java.util.*;

class ApeIntelligence {
    // initializing scanner for the whole program
    public static Scanner input = new Scanner(System.in);

    // initializing counters of small boxes for the 3rd pard of the task
    public static int counterSmallBoxes = 0;
    public static int counterAllBoxes = 0;

    // function which receives translated English text without proper dimensions and
    // fixes that: describes dimensions of all boxes
    public String changeDimensions(String out) {
        if (counterSmallBoxes != counterAllBoxes) {
            // countersmallboxes-counterallboxes= the amount of 2x2x2 boxes, thus it fixes
            // the tarnslation for those boxes
            counterAllBoxes--;
            out = out.replaceFirst("xxxxx", "2x2x2");
            return changeDimensions(out);
        } else
            return out.replaceAll("xxxxx", "1x1x1");// fixes the translation for the rest small boxes
    }

    // function that adds painting names on the Base
    public String addPaintings() {
        int painting = input.nextInt();
        if (painting != -1) {
            // counts how many small boxes there are
            counterSmallBoxes++;
            // adds texts about the painting on 1x1x1
            return " with " + findResource(painting) + " painted on the sides" + addPaintings();
        }
        return "";
    }

    // function that writes the translation for the base part
    public String constructBase() {
        int box = input.nextInt();
        if (box != 4) {
            // counts how many boxes in total there are
            counterAllBoxes++;
            return " on top of which is a xxxxx " + findItemType(box) + " cube" + constructBase();
        }
        return "";
    }

    // function that packs a container
    public String packContainer(boolean read, int thisItemType) {
        int nextItemType, item, itemType;

        // checking wether the next item type was inputted or not
        if (read) {
            itemType = input.nextInt();
        } else
            itemType = thisItemType;
        item = input.nextInt();
        // reading next number in order to follow further steps
        nextItemType = input.nextInt();

        //checking wether there top of pack is reached or not
        if (nextItemType != 4) {
            if (nextItemType == 5) {
                return findItemType(itemType) + " " + findItem(item) + " containing a "
                        + packContainer(true, nextItemType) + " and a " + packContainer(true, nextItemType);
            } else if (0 < nextItemType && nextItemType < 4)
                return findItemType(itemType) + " " + findItem(item) + " containing a "
                        + packContainer(false, nextItemType);
            else
                return "...";
        } else
            return findItemType(itemType) + " " + findItem(item);
        /// return out;
    }

    // function that finds directions where container must be sent
    public String findDirections() {
        int direction;
        direction = input.nextInt();
        if (direction != -1) {
            return " and send one to " + findDirection(direction) + findDirections();
        }
        return "";
    }

    // function that translates apes' describtion of items to English
    public String findItem(int n) {

        String out;
        switch (n) {
            case 0:
                out = "cage";
                break;
            case 1:
                out = "net";
                break;
            case 2:
                out = "helmet";
                break;
            case 3:
                out = "shield";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of item types to English
    public String findItemType(int n) {

        String out;
        switch (n) {
            case 0:
                out = "wooden";
                break;
            case 1:
                out = "steel";
                break;
            case 2:
                out = "stone";
                break;
            case 3:
                out = "cotton";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of place to retreat to English
    public String findPlaceToRetreat(int n) {
        String out;
        switch (n) {
            case 0:
                out = "Ape City";
                break;
            case 1:
                out = "Forbidden Zone";
                break;
            case 2:
                out = "Rocky Mountains";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of searched objects to English
    public String findSearchedObject(int n) {
        String out;
        switch (n) {
            case 0:
                out = "humans";
                break;
            case 1:
                out = "human males";
                break;
            case 2:
                out = "human females";
                break;
            case 3:
                out = "human children";
                break;
            case 4:
                out = "mutated humans";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of directions to English
    public String findDirection(int n) {
        String out;
        switch (n) {
            case 1:
                out = "North";
                break;
            case 2:
                out = "East";
                break;
            case 3:
                out = "West";
                break;
            case 4:
                out = "South";
                break;
            case 5:
                out = "South-East";
                break;
            case 6:
                out = "South-West";
                break;
            case 7:
                out = "North-East";
                break;
            case 8:
                out = "North-West";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of places to English
    public String findPlace(int n) {
        String out;
        switch (n) {
            case 0:
                out = "hills";
                break;
            case 1:
                out = "marshes";
                break;
            case 2:
                out = "caves";
                break;
            case 3:
                out = "woods";
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that translates apes' describtion of resources to English
    public String findResource(int n) {
        String out;
        switch (n) {
            case 1:
                out = ("orangutans");
                break;
            case 2:
                out = ("chimps");
                break;
            case 3:
                out = ("gorillas");
                break;
            case 4:
                out = ("mounted chimps");
                break;
            case 5:
                out = ("mounted orangutans");
                break;
            default:
                out = "...";
                break;
        }
        return out;
    }

    // function that outputs the command of attacking with resourcees of apes to
    // english
    public void attackWithResources(int quantity, int resource) {
        if (quantity == 0) {
            System.out.println("Attack with all your " + findResource(resource));
        } else if (quantity > 0) {
            System.out.println("Attack with " + quantity + findResource(resource));
        } else
            System.out.println("Attack with ... " + findResource(resource));
    }

    // function that outputs the command of search request of apes to english
    public void searchRequest(int mandatory) {
        String out = "Search ";
        int quantity, place, additional, endNumber;
        if (mandatory == 0) {
            quantity = input.nextInt();
            place = input.nextInt();
            if (quantity > 0) {
                out = out + quantity + " " + findPlace(place);
            } else
                out = out + "... " + findPlace(place);

        } else if (mandatory > 0) {
            out = out + findDirection(mandatory);
        } else
            out = out + "... ";

        // adds the additional part of the translations
        additional = input.nextInt();
        if (additional != -1) {
            out = out + " and look for " + findSearchedObject(additional);
            endNumber = input.nextInt();
        }

        // outputs the result
        System.out.println(out);
    }

    // functions that outputs the command of ordeer to retreat of apes to english
    public void orderToRetreat(int mandatory) {
        String out = "Retreat to " + findPlaceToRetreat(mandatory);
        int additional = input.nextInt();

        // initializing number which will be read as -1 orr the end number
        int endNumber;

        // checking whether there is additional part or not
        if (additional != -1) {
            int resource = input.nextInt();
            int direction = input.nextInt();
            if (additional > 0) {
                out = out + " and move " + additional + " " + findResource(resource) + " to "
                        + findDirection(direction);
            } else if (additional == 0) {
                out = out + " and move all your " + findResource(resource) + " to " + findDirection(direction);
            } else if (additional < 0)
                out = out + " and move ... " + findResource(resource) + " to " + findDirection(direction);
            endNumber = input.nextInt();
        }

        //ooutputing the text
        System.out.println(out);
    }

    // constructing full translation for the for part 1; checking which subcommand
    // should be executed
    public void part1() {
        int command = input.nextInt();
        int quantity, resource, endNumber, mandatory;
        switch (command) {
            case 0:
                quantity = input.nextInt();
                resource = input.nextInt();
                endNumber = input.nextInt();
                attackWithResources(quantity, resource);
                break;
            case 1:
                mandatory = input.nextInt();
                searchRequest(mandatory);
                break;
            case 2:
                mandatory = input.nextInt();
                orderToRetreat(mandatory);
                break;
            default:
                System.out.println("...");
                break;
        }
    }

    // constructing full translation for the pack container command of apes (for
    // part 2)
    public void part2() {
        String out = "Pack a " + packContainer(true, 0) + findDirections();
        System.out.println(out);
    }

    // constructing full translation for the base construction command of apes (for
    // part 3)
    public void part3() {
        String out = "Build a concrete base" + constructBase() + addPaintings();
        System.out.println(changeDimensions(out));
    }

    public static void main(String[] args) {
        ApeIntelligence ape = new ApeIntelligence();
        // gets from input which task should be carried out
        String task = input.nextLine();
        if (task.equals("Part 1"))
            ape.part1();
        if (task.equals("Part 2"))
            ape.part2();
        if (task.equals("Part 3"))
            ape.part3();
        input.close();
    }
}