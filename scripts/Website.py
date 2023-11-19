import json
import Recipe
from scripts.Scraper import Scraper


class Website:
    def __init__(self, recipe, nutritionFacts, ingredients):
        self.recipe = recipe
        self.nutrition_facts = nutritionFacts
        self.ingredients = ingredients

    def to_json(self):
        return json.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4)

l = ["https://www.hellofresh.de/recipes/afrikanischer-erdnusseintopf-mit-borlottibohnen-5f476dd3f92f3a1d1b5e44ce"]

result = []

for i in l:
    scraper = Scraper(i)
    soup = scraper.url_to_soup()

    # Create instances of Recipe, NutritionFacts, and Ingredients using the soup
    recipe = scraper.soup_to_recipe(soup)
    nutrition_facts = scraper.soup_to_nutrition_facts(soup)
    ingredients = scraper.soup_to_ingredient(soup)

    # Create a Website instance
    website = Website(recipe, nutrition_facts, ingredients)

    # Convert the Website object to JSON
    result.append(website)


j = json.dumps(result, default=lambda o: o.__dict__, sort_keys=True, indent=4)


f = open("d.json", "w")

f.write(j)

f.close()

