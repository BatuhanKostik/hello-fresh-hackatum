import requests
from bs4 import BeautifulSoup
class Scrape:
    def __init__(self, url):
        self.url = url

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

    def soup_to_ingredient(self, soup):
        # Find all elements with class="sc-a6821923-0 kOxEZP"
        grid = soup.find_all("div", {"class": "sc-a6821923-0 kOxEZP"})

        # Extract text from each paragraph
        ingredient_texts = [p.get_text(strip=True) for p in soup.find_all('p', class_='sc-a6821923-0 fLfTya')]

        # Print the extracted text
        for ingredient in ingredient_texts:
            print(ingredient)

    # Get the HTML of the page
scrape = Scrape("https://www.hellofresh.de/recipes/balsamico-linsen-salat-mit-hahnchenbrust-64df2a75552e10127649f25f")
soup = scrape.url_to_soup()
scrape.soup_to_ingredient(soup)

