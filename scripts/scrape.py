import requests
from bs4 import BeautifulSoup

try:
    # Get the HTML of the page
    url = "https://www.hellofresh.de/recipes/balsamico-linsen-salat-mit-hahnchenbrust-64df2a75552e10127649f25f"
    response = requests.get(url)
    response.raise_for_status()  # Raise an HTTPError for bad responses

    # Parse the HTML content into a BeautifulSoup object
    soup = BeautifulSoup(response.text, "html.parser")

    # Find all elements with class="sc-a6821923-0 kOxEZP"
    elements = soup.find_all("div", {"class": "sc-a6821923-0 kOxEZP"})

    # Store ingredient information
    ingredient_data = []

    for element in elements:
        # Assuming ingredient name is in a specific tag and class within the element
        ingredient_name = element.find("span", {"class": "sc-a6821923-0 fLfTya"}).text

        # Assuming image URL is in a specific tag and class within the element
#        image_url = element.find("img", {"class": "sc-a6821923-0 hZWAJE"})["src"]

        # Store the data for this ingredient
        ingredient_data.append({
            "ingredient_name": ingredient_name,
#            "image_url": image_url
        })

    # Print or further process the stored data
    for data in ingredient_data:
        print("Ingredient Name:", data["ingredient_name"])
        print("Image URL:", data["image_url"])

    # Print the found elements
    print(elements)

except requests.exceptions.RequestException as e:
    print(f"Error making the request: {e}")
except Exception as e:
    print(f"An unexpected error occurred: {e}")
