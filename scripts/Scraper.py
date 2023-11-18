import requests
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
        # Find all elements with class="sc-a6821923-0 kOxEZP"
        grid = soup.find_all("div", {"class": "sc-a6821923-0 KGVMo"})

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('p', class_='sc-a6821923-0 KGVMo')]

        # Print the extracted text
        for ingredient in ingredient_texts:
            print(ingredient)

    # 2: Ingredients Scraper
    def soup_to_ingredient(self, soup):
        # Find all elements with class="sc-a6821923-0 kOxEZP"
        grid = soup.find_all("div", {"class": "sc-a6821923-0 kOxEZP"})

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('p', class_='sc-a6821923-0 fLfTya')]

        # Print the extracted text
        for ingredient in ingredient_texts:
            print(ingredient)

        # Extract text from each paragraph
        ingredient_images = [p.find_all('img')[-1]['src'] for p in soup.find_all('p', class_='sc-a6821923-0 hZWAJE')]

        # Print the extracted text
        for images in ingredient_images:
            print(images)



scrape = Scraper("https://www.hellofresh.de/recipes/feines-rinderhuftsteak-mit-cowboy-butter-sosse-6508729632e9107c6db96555")
soup = scrape.url_to_soup()
scrape.soup_to_ingredient(soup)

