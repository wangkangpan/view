import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC





def selectContentByid(url):
    try:
        co = webdriver.ChromeOptions()
        co.headless = True
        co.add_argument('--headless')
        co.add_argument('--disable-gpu')

        driver = webdriver.Chrome('D:\seleniumwebdriver\chromedriver.exe')
        driver.get(url)


        text = WebDriverWait(driver, 10, 0.5).until(
            EC.presence_of_element_located((By.XPATH, '//*[@id = "articleContentId"]'))
        )

        return text.text

    except:
        pass
    finally:
        driver.quit()

if __name__ == '__main__':
    args = []
    for i in range(1, len(sys.argv)):
        args.append(sys.argv[i])

    print(selectContentByid(args[0]))
