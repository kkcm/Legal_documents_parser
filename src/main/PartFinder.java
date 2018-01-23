package main;

public class PartFinder {
        private ObjectHierarchy hierarchy;

    public PartFinder(){
        this.hierarchy = new ObjectHierarchy();
    }

    public Integer findChapterOrSection (StructBuilder struct, String objectName, IPart object){
        Integer i;

        for (i=0; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);

            do {
                while (part.getUp() != null) {
                    part = part.getUp();
                    if (hierarchy.isEqual(part, object)) {
                        String str = part.getDescription();
                        if (str != null && str.contains(objectName)) {
                            return i;
                        } else {
                            break;
                        }
                    }
                }
                part = part.getRight();
            } while (part != null);
        }
        throw new TestException("Podany element nie istnieje.");
    }

    public Integer findNextChapterOrSection (StructBuilder struct, int num, IPart object){
        Integer i;
        for (i=num+1; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);

            do{
                while (part.getUp() != null){
                    part = part.getUp();
                    if (hierarchy.isEqual(part, object)){
                        return i;
                    }
                }
                part = part.getRight();
            } while (part != null);
        }

        if (i == struct.articles.size()){
            return struct.articles.size();
        }
        throw new TestException("Podany element nie istnieje.");
    }

    public IPart findArticleWithLetter(IPart article, String partName){
        if(article.getRight() != null){
            article = article.getRight();
            String str = article.getDescription();
            if(str.contains(partName)){
                return article;
            } else if (article.getRight() != null){
                return this.findNextPart(new ArticleWithLetter(), partName, article.getRight());
            } else {
            throw new TestException("Podany element nie istnieje.");
            }
        }
        throw new TestException("Podany element nie istnieje.");
    }


    public IPart findNextPart(IPart simplePart, String partName, IPart partIn){
        if(partIn == null){
            throw new TestException("Podany element nie istnieje.");
        }


        IPart partToReturn = partIn;
        IPart partS = partIn;

        if(hierarchy.isEqual(partS, simplePart)){
            String str = partS.getDescription();
            if(str.contains(partName)){
                partToReturn = partS;
            } else if (partS.getRight() != null){
                partToReturn = findNextPart(simplePart, partName, partS.getRight());
            } else {
                throw new TestException("Podany element nie istnieje.");
            }
        } else {
            throw new TestException("Podany element nie istnieje.");
        }
        return partToReturn;
    }
}

