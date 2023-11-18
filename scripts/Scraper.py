import requests
import re
from bs4 import BeautifulSoup


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
        recipe_title = description_div.find('h1').get_text(strip=True)
        recipe_sub_title = description_div.find('h2').get_text(strip=True)
        print("H1:", recipe_title)
        print("H2:", recipe_sub_title)

        description_div = soup.find('div', class_='sc-a6821923-0 kVUALj')
        description = description_div.find('p').get_text(strip=True)
        print("Description", description)

        # Find the div with the specified class
        tags_div = soup.find('div', class_='sc-a6821923-0 jCgtKL')
        tags = [tag.get_text(strip=True) for tag in tags_div.find_all('span', class_='sc-a6821923-0 fivcnB')]
        print("Tags:", tags)

        # Find the div with the specified class
        recipe_details_div = soup.find('div', class_='sc-a6821923-0 kuiNX')

        # Extract and store the recipe details
        preparation_time = recipe_details_div.find('span', {'data-translation-id': 'recipe-detail.preparation-time'}).find_next('span').get_text(strip=True)
        cooking_time = recipe_details_div.find('span', {'data-translation-id': 'recipe-detail.cooking-time'}).find_next('span').get_text(strip=True)
        difficulty_level = recipe_details_div.find('span', {'data-translation-id': 'recipe-detail.difficulty'}).find_next('span').get_text(strip=True)
        print("Preparation Time:", preparation_time)
        print("Cooking Time:", cooking_time)
        print("Difficulty Level:", difficulty_level)



    # 2: Nutrition Facts Scraper

    def soup_to_nutrition_facts(self, soup):

        html_content = str(soup)

        # Use a regular expression to find all numeric values
        numeric_values = re.findall(r'\d+\.\d+|\d+', html_content)

        # Print the extracted numeric values
        print(numeric_values)


        print("Nutrition Information:" + nutrition_div.get_text(strip=True))










    # 3: Ingredients Scraper
    def soup_to_ingredient(self, soup):
        # Find all elements with class="sc-a6821923-0 kOxEZP"
        grid_ingredient = soup.find_all("div", {"class": "sc-a6821923-0 kOxEZP"})

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('p', class_='sc-a6821923-0 fLfTya')]

        # Print the extracted text
        for ingredient in ingredient_texts:
            print("Ingredient: " + ingredient) # Text works perfectly fine but images not










scrape = Scraper("https://www.hellofresh.de/recipes/couscous-mit-dukkah-gemuse-and-hirtenkase-thermomix-650819fe270f68660e1ac70b")
soup = scrape.url_to_soup()
scrape.soup_to_nutrition_facts(soup)

