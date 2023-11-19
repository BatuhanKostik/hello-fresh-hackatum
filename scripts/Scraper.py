import html.parser

import requests
from bs4 import BeautifulSoup
from html import unescape

from scripts.Ingredients import Ingredients
from scripts.NutritionFacts import NutritionFacts
from scripts.Recipe import Recipe


class Scraper:

    def __init__(self, url):
        self.url = url

    """
        Retrieves the HTML content from the specified URL and parses it into a BeautifulSoup object.

        Args:
            self (object): The instance of the class calling the method.

        Returns:
            BeautifulSoup: A BeautifulSoup object representing the HTML content of the URL.
        """

    def url_to_soup(self):
        try:
            response = requests.get(self.url)
            # Parse the HTML content into a BeautifulSoup object
            soup = BeautifulSoup(response.text, "html.parser")
            return soup
        except requests.exceptions.RequestException as e:
            print(f"Error making the request: {e}")
        except Exception as e:
            print(f"An unexpected error occurred: {e}")

    # 1: Recipe Scraper
    def soup_to_recipe(self, soup):
        # Find the div with the specified class
        description_div = soup.find('div', class_='sc-a6821923-0 jeetYO')
        recipe_title = unescape(description_div.find('h1').get_text(strip=True))

        recipe_sub_title = unescape(description_div.find('h2').get_text(strip=True))

        description_div = soup.find('div', class_='sc-a6821923-0 kVUALj')
        description = description_div.find('p').get_text(strip=True)

        # Find the div with the specified class
        tags_div = soup.find('div', class_='sc-a6821923-0 jCgtKL')
        receipe_tags = [tag.get_text(strip=True) for tag in tags_div.find_all('span', class_='sc-a6821923-0 fivcnB')]

        # Find the div with the specified class
        recipe_details_div = soup.find('div', class_='sc-a6821923-0 kuiNX')

        # Extract and store the recipe details
        total_time = recipe_details_div.find('span',
                                             {'data-translation-id': 'recipe-detail.preparation-time'}).find_next(
            'span').get_text(strip=True)
        cooking_time = recipe_details_div.find('span', {'data-translation-id': 'recipe-detail.cooking-time'}).find_next(
            'span').get_text(strip=True)
        difficulty_level = recipe_details_div.find('span',
                                                   {'data-translation-id': 'recipe-detail.difficulty'}).find_next(
            'span').get_text(strip=True)

        r = Recipe(recipe_title, recipe_sub_title, description, total_time, cooking_time, difficulty_level)
        r.tags.append(receipe_tags)
        return r

    # 2: Nutrition Facts Scraper

    def soup_to_nutrition_facts(self, soup):

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('span', class_='sc-a6821923-0 eZjiGJ')]
        del ingredient_texts[:3]
        return NutritionFacts(ingredient_texts[0], ingredient_texts[1], ingredient_texts[2], ingredient_texts[3],
                           ingredient_texts[4], ingredient_texts[5], ingredient_texts[6])

    """
    # Print the extracted text
    for ingredient in ingredient_texts:
         print("Ingredient: " + ingredient) 
    """

    # 3: Ingredients Scraper
    def soup_to_ingredient(self, soup):

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('p', class_='sc-a6821923-0 fLfTya')]

        i = Ingredients(
            "https://www.hellofresh.de/recipes/couscous-mit-dukkah-gemuse-and-hirtenkase-thermomix-650819fe270f68660e1ac70b")
        i.ingredients.append(ingredient_texts)

        return i


