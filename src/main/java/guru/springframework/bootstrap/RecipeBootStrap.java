package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public RecipeBootStrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("#####################  started data loading");
		recipeRepository.saveAll(getRecipes());
		log.info("#####################  data loaded successfully");
	}

	
	private java.util.List<Recipe> getRecipes(){
		
		java.util.List<Recipe> recipes = new ArrayList<Recipe>();
		
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		if(!eachUomOptional.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if(!tablespoonUomOptional.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
		if(!pinchUomOptional.isPresent() ) {
			throw new RuntimeException("Expected UOM not found");
		}
		
		UnitOfMeasure uomEach = eachUomOptional.get();
		UnitOfMeasure uomTablespoon = tablespoonUomOptional.get();
		UnitOfMeasure uomPinch = pinchUomOptional.get();
		
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if(!mexicanCategoryOptional.isPresent() ) {
			throw new RuntimeException("Expected Category not found");
		}
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent() ) {
			throw new RuntimeException("Expected Category not found");
		}
		
		Category categoryMexican =  mexicanCategoryOptional.get();
		Category categoryAmerican = americanCategoryOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacmole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDescription("fafaaaaaaaaaaaaaaaaaaaafafadşl 4546456a afadpaf 65666646f4 adafsdfadfasfda 46464646 afadfadf 666 fafa");
		
		Notes guacNotes = new Notes();
		guacNotes.setReceiptNotes("Great note guacrecipe");
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.addIngredient(new Ingredient("each uom", new BigDecimal(2), uomEach));
		guacRecipe.addIngredient(new Ingredient("Tablespoon uom", new BigDecimal(3), uomTablespoon));
		guacRecipe.addIngredient(new Ingredient("Pinch uom", new BigDecimal(13), uomPinch));
		
		guacRecipe.getCategories().add(categoryMexican);
		guacRecipe.getCategories().add(categoryAmerican);
		
		recipes.add(guacRecipe);
		
		
		Recipe turkishRecipe = new Recipe();
		turkishRecipe.setDescription("Perfect Guacmole");
		turkishRecipe.setPrepTime(50);
		turkishRecipe.setCookTime(15);
		turkishRecipe.setDifficulty(Difficulty.HARD);
		turkishRecipe.setDescription("fafaaaaaaaaaaaaaaaaaaaafafadşl 4546456a afadpaf 65666646f4 adafsdfadfasfda 46464646 afadfadf 666 fafa");
		
		Notes turkishNotes = new Notes();
		turkishNotes.setReceiptNotes("Great note Turkish desert");
		turkishRecipe.setNotes(turkishNotes);
		
		turkishRecipe.addIngredient(new Ingredient("each uom", new BigDecimal(2), uomEach));
		turkishRecipe.addIngredient(new Ingredient("Tablespoon uom", new BigDecimal(3), uomTablespoon));
		
		turkishRecipe.getCategories().add(categoryMexican);
		
		recipes.add(turkishRecipe);
		
		return recipes;
	}



	
	
	
	

}
