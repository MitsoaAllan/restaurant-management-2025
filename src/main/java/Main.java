import dao.DishDAO;
import dao.IngredientDAO;
import entity.Criteria;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DishDAO subjectDish = new DishDAO();
        IngredientDAO subjectIngredient = new IngredientDAO();
//        System.out.println(subject.getAll(4,1));
//        System.out.println(subjectDish.getIngredientsDish(1));

//        System.out.println(subjectIngredient.getAll(4,1);

        Criteria criteria = new Criteria("name","u", "ILIKE",true,true,"AND");
        Criteria criteria1 = new Criteria("unit_price","1000", "<=",false,false,"AND");

        System.out.println(subjectIngredient.getIngredientsByCriteria(List.of(criteria,criteria1),4,1));

    }
}