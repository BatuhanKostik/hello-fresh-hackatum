import requests
from bs4 import BeautifulSoup

try:

    # Get the HTML of the page
    url = "https://www.hellofresh.de/recipes/balsamico-linsen-salat-mit-hahnchenbrust-64df2a75552e10127649f25f"
    response = requests.get(url)
#    response.raise_for_status()  # Raise an HTTPError for bad responses

    # Parse the HTML content into a BeautifulSoup object
    soup = BeautifulSoup(response.text, "html.parser")

    # Find all elements with class="sc-a6821923-0 kOxEZP"
    grid = soup.find_all("div", {"class": "sc-a6821923-0 kOxEZP"})
    grid2 = soup.find_all("p", {"class": "sc-a6821923-0 fLfTya"})

    # Print all elements of the grid
    for element in grid2:
        print(element)




except requests.exceptions.RequestException as e:
    print(f"Error making the request: {e}")
except Exception as e:
    print(f"An unexpected error occurred: {e}")

