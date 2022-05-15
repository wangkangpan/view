import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC



import sys
import io
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf8')


def selectContentByid(url, tag, key, value):
    try:

        co = webdriver.ChromeOptions()
        co.headless = True
        co.add_argument('--headless')
        co.add_argument('--disable-gpu')

        driver = webdriver.Chrome('D:\seleniumwebdriver\chromedriver.exe', options=co)
        # driver = webdriver.Chrome('D:\seleniumwebdriver\chromedriver.exe')

        driver.get(url)

        text = WebDriverWait(driver, 10, 0.5).until(
            EC.presence_of_all_elements_located((By.XPATH, '//' + tag + '[@' + key + ' = "' + value + '"]'))
        )
        for i in text:
            print(i.text)
        # return text.text if (text.get_attribute('value') is None or text.get_attribute('value') == '') \
        #     else text.get_attribute('value')

    except Exception as e:
        print(str(e))
    finally:
        driver.quit()


if __name__ == '__main__':
    args = []
    for i in range(1, len(sys.argv)):
        args.append(sys.argv[i])
    # args.append('https://www.baidu.com/')
    # args.append('span')
    # args.append('clas')
    # args.append('title-content-title')
    selectContentByid(args[0], args[1], args[2], args[3])

