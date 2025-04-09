-- First, clear any existing data to avoid conflicts
DELETE FROM experience_reviews;
DELETE FROM interview_steps;
DELETE FROM job_listings;
DELETE FROM companies;
DELETE FROM users;

-- Insert admin user
INSERT INTO users (id, username, password, email, role)
VALUES (
           1,
           'admin',
           '$2a$12$aXjrYqWQFsHsoZ5cP81oiOrZkLua9q99RM0roPKn0.c2H6bQ2pfzq',
           'admin@example.com',
           'ADMIN'
       ) ON DUPLICATE KEY UPDATE
    username = VALUES(username);

-- Insert companies with error handling
INSERT INTO companies (name, description, location, website) VALUES
-- FAANG and Major Tech
('Google', 'A multinational technology company specializing in Internet-related services and products.', 'Mountain View, California', 'https://www.google.com'),
('Amazon', 'A multinational technology company focusing on e-commerce, cloud computing, and artificial intelligence.', 'Seattle, Washington', 'https://www.amazon.com'),
('Apple', 'A multinational technology company that designs, develops, and sells consumer electronics, computer software, and online services.', 'Cupertino, California', 'https://www.apple.com'),
('Microsoft', 'A multinational technology corporation that develops, manufactures, licenses, supports, and sells computer software, consumer electronics, and personal computers.', 'Redmond, Washington', 'https://www.microsoft.com'),
('Meta', 'A technology company that develops social media platforms and virtual reality products.', 'Menlo Park, California', 'https://www.meta.com'),
('Netflix', 'A streaming entertainment service company offering a wide variety of TV shows, movies, and documentaries.', 'Los Gatos, California', 'https://www.netflix.com'),
('Tesla', 'An electric vehicle and clean energy company that designs and manufactures electric cars, battery energy storage, and solar panels.', 'Austin, Texas', 'https://www.tesla.com'),
('Uber', 'A technology company that develops a mobile application for ride-hailing, food delivery, and package delivery.', 'San Francisco, California', 'https://www.uber.com'),
('Airbnb', 'An online marketplace for lodging, primarily homestays for vacation rentals, and tourism activities.', 'San Francisco, California', 'https://www.airbnb.com'),
('Twitter', 'A social media platform for microblogging and social networking.', 'San Francisco, California', 'https://www.twitter.com'),

-- Enterprise Software
('Oracle', 'A multinational computer technology corporation that sells database software and technology, cloud engineered systems, and enterprise software products.', 'Austin, Texas', 'https://www.oracle.com'),
('SAP', 'A German multinational software corporation that makes enterprise software to manage business operations and customer relations.', 'Walldorf, Germany', 'https://www.sap.com'),
('Salesforce', 'A cloud-based software company that provides customer relationship management service and a suite of enterprise applications.', 'San Francisco, California', 'https://www.salesforce.com'),
('Adobe', 'A multinational computer software company that develops and sells creative, marketing, and document management software.', 'San Jose, California', 'https://www.adobe.com'),
('VMware', 'A cloud computing and virtualization technology company that provides cloud and virtualization software and services.', 'Palo Alto, California', 'https://www.vmware.com'),
('ServiceNow', 'A cloud computing company that provides IT service management, IT operations management, and IT business management.', 'Santa Clara, California', 'https://www.servicenow.com'),
('Workday', 'A cloud-based software vendor that specializes in human capital management and financial management applications.', 'Pleasanton, California', 'https://www.workday.com'),
('Intuit', 'A business and financial software company that develops and sells financial, accounting, and tax preparation software.', 'Mountain View, California', 'https://www.intuit.com'),
('Atlassian', 'An Australian software company that develops products for software developers, project managers, and content management.', 'Sydney, Australia', 'https://www.atlassian.com'),
('Zendesk', 'A service-first CRM company that builds software designed to improve customer relationships.', 'San Francisco, California', 'https://www.zendesk.com'),

-- Cloud & Infrastructure
('DigitalOcean', 'A cloud infrastructure provider that offers cloud computing services to software developers and businesses.', 'New York, New York', 'https://www.digitalocean.com'),
('Cloudflare', 'A web infrastructure and website security company that provides content delivery network services, DDoS mitigation, and Internet security.', 'San Francisco, California', 'https://www.cloudflare.com'),
('Fastly', 'An edge cloud platform that enables developers to build, secure, and deliver digital experiences.', 'San Francisco, California', 'https://www.fastly.com'),
('Akamai', 'A content delivery network and cloud service provider that helps businesses deliver secure, high-performing user experiences.', 'Cambridge, Massachusetts', 'https://www.akamai.com'),
('HashiCorp', 'A software company that provides open-source tools and commercial products that enable developers to provision, secure, and run cloud infrastructure.', 'San Francisco, California', 'https://www.hashicorp.com'),
('Databricks', 'A data and AI company that helps organizations prepare and process large amounts of data for analytics and machine learning.', 'San Francisco, California', 'https://www.databricks.com'),
('Snowflake', 'A cloud-based data-warehousing company that provides data storage and analytics services.', 'San Mateo, California', 'https://www.snowflake.com'),
('MongoDB', 'A document-oriented database program that provides high performance, high availability, and easy scalability.', 'New York, New York', 'https://www.mongodb.com'),
('Elastic', 'A search company that builds self-managed and SaaS offerings that make data usable in real time and at scale.', 'Mountain View, California', 'https://www.elastic.co'),
('Confluent', 'A company that provides a streaming platform based on Apache Kafka.', 'Mountain View, California', 'https://www.confluent.io'),

-- FinTech
('Stripe', 'A technology company that builds economic infrastructure for the internet.', 'San Francisco, California', 'https://www.stripe.com'),
('Square', 'A financial services and digital payments company that provides payment processing software and hardware.', 'San Francisco, California', 'https://www.square.com'),
('Robinhood', 'A financial services company that offers commission-free trading of stocks, ETFs, and cryptocurrencies.', 'Menlo Park, California', 'https://www.robinhood.com'),
('Coinbase', 'A cryptocurrency exchange platform that provides a platform for trading various cryptocurrencies.', 'San Francisco, California', 'https://www.coinbase.com'),
('Plaid', 'A financial services company that builds technology infrastructure for financial services.', 'San Francisco, California', 'https://www.plaid.com'),
('Revolut', 'A British financial technology company that offers banking services including currency exchange, debit cards, and peer-to-peer payments.', 'London, United Kingdom', 'https://www.revolut.com'),
('TransferWise', 'A financial technology company that provides international money transfer services.', 'London, United Kingdom', 'https://www.wise.com'),
('Chime', 'A financial technology company that provides fee-free mobile banking services.', 'San Francisco, California', 'https://www.chime.com'),
('Affirm', 'A financial technology company that offers installment loans to consumers at the point of sale.', 'San Francisco, California', 'https://www.affirm.com'),
('SoFi', 'An online personal finance company that provides student loan refinancing, mortgages, and personal loans.', 'San Francisco, California', 'https://www.sofi.com'),

-- Cybersecurity
('Palo Alto Networks', 'A cybersecurity company that provides advanced firewalls and cloud-based administration.', 'Santa Clara, California', 'https://www.paloaltonetworks.com'),
('CrowdStrike', 'A cybersecurity technology company that provides cloud workload and endpoint security.', 'Sunnyvale, California', 'https://www.crowdstrike.com'),
('Okta', 'A company that provides identity management services for the cloud.', 'San Francisco, California', 'https://www.okta.com'),
('Zscaler', 'A cloud security company that provides internet security, web security, and cloud security services.', 'San Jose, California', 'https://www.zscaler.com'),
('Fortinet', 'A cybersecurity company that develops and sells security solutions such as physical firewalls and antivirus software.', 'Sunnyvale, California', 'https://www.fortinet.com'),
('Proofpoint', 'A cybersecurity company that provides email security, data loss prevention, and electronic discovery products.', 'Sunnyvale, California', 'https://www.proofpoint.com'),
('Rapid7', 'A cybersecurity company that provides vulnerability management, penetration testing, and incident detection and response.', 'Boston, Massachusetts', 'https://www.rapid7.com'),
('Tenable', 'A cybersecurity company that provides vulnerability management solutions.', 'Columbia, Maryland', 'https://www.tenable.com'),
('Darktrace', 'A cybersecurity company that uses artificial intelligence to detect and respond to cyber threats.', 'Cambridge, United Kingdom', 'https://www.darktrace.com'),
('SentinelOne', 'A cybersecurity company that provides endpoint security solutions.', 'Mountain View, California', 'https://www.sentinelone.com'),

-- AI & Machine Learning
('OpenAI', 'An AI research and deployment company focused on ensuring artificial general intelligence benefits all of humanity.', 'San Francisco, California', 'https://www.openai.com'),
('DeepMind', 'A British artificial intelligence subsidiary of Alphabet Inc. that researches artificial intelligence.', 'London, United Kingdom', 'https://www.deepmind.com'),
('Anthropic', 'An AI safety and research company that develops large language models and AI assistants.', 'San Francisco, California', 'https://www.anthropic.com'),
('Hugging Face', 'A company that develops tools for building applications using machine learning.', 'New York, New York', 'https://www.huggingface.co'),
('Scale AI', 'A company that provides data labeling and validation services for machine learning.', 'San Francisco, California', 'https://www.scale.com'),
('DataRobot', 'An enterprise AI platform that helps organizations build and deploy machine learning models.', 'Boston, Massachusetts', 'https://www.datarobot.com'),
('C3.ai', 'An enterprise AI software provider that develops software for developing, deploying, and operating large-scale AI applications.', 'Redwood City, California', 'https://www.c3.ai'),
('Clarifai', 'An AI company that provides image and video recognition technology.', 'New York, New York', 'https://www.clarifai.com'),
('Sift', 'A machine learning company that provides fraud prevention and risk management solutions.', 'San Francisco, California', 'https://www.sift.com'),
('Cerebras', 'A company that develops computer systems for artificial intelligence and deep learning applications.', 'Los Altos, California', 'https://www.cerebras.net'),

-- Developer Tools & Platforms
('GitHub', 'A platform for version control and collaboration that lets developers work together on projects.', 'San Francisco, California', 'https://www.github.com'),
('GitLab', 'A web-based DevOps lifecycle tool that provides a Git-repository manager providing wiki, issue-tracking and CI/CD pipeline features.', 'San Francisco, California', 'https://www.gitlab.com'),
('Docker', 'A company that develops software for operating-system-level virtualization also known as containerization.', 'San Francisco, California', 'https://www.docker.com'),
('Kubernetes', 'An open-source container orchestration platform for automating deployment, scaling, and management of containerized applications.', 'San Francisco, California', 'https://www.kubernetes.io'),
('CircleCI', 'A continuous integration and delivery platform that helps software teams rapidly release code.', 'San Francisco, California', 'https://www.circleci.com'),
('Travis CI', 'A hosted continuous integration service used to build and test software projects hosted on GitHub.', 'Berlin, Germany', 'https://www.travis-ci.com'),
('SonarSource', 'A company that develops tools for continuous code quality inspection.', 'Geneva, Switzerland', 'https://www.sonarsource.com'),
('Sentry', 'A company that provides application monitoring and error tracking software.', 'San Francisco, California', 'https://www.sentry.io'),
('New Relic', 'A software analytics company that provides cloud-based solutions for monitoring and troubleshooting applications.', 'San Francisco, California', 'https://www.newrelic.com'),
('Datadog', 'A monitoring and analytics platform for cloud-scale applications.', 'New York, New York', 'https://www.datadoghq.com'),

-- E-commerce & Retail Tech
('Shopify', 'A commerce platform that allows anyone to set up an online store and sell their products.', 'Ottawa, Canada', 'https://www.shopify.com'),
('Etsy', 'An e-commerce website focused on handmade or vintage items and craft supplies.', 'Brooklyn, New York', 'https://www.etsy.com'),
('Wayfair', 'An e-commerce company that sells furniture and home goods.', 'Boston, Massachusetts', 'https://www.wayfair.com'),
('Chewy', 'An online retailer of pet food and other pet-related products.', 'Dania Beach, Florida', 'https://www.chewy.com'),
('Wish', 'An online e-commerce platform that facilitates transactions between sellers and buyers.', 'San Francisco, California', 'https://www.wish.com'),
('MercadoLibre', 'An e-commerce company that provides online marketplaces for e-commerce and online auctions.', 'Buenos Aires, Argentina', 'https://www.mercadolibre.com'),
('Rakuten', 'A Japanese electronic commerce and online retailing company.', 'Tokyo, Japan', 'https://www.rakuten.com'),
('JD.com', 'A Chinese e-commerce company that specializes in online retail.', 'Beijing, China', 'https://www.jd.com'),
('Alibaba', 'A Chinese multinational technology company specializing in e-commerce, retail, Internet, and technology.', 'Hangzhou, China', 'https://www.alibaba.com'),
('Flipkart', 'An Indian e-commerce company that sells products online.', 'Bangalore, India', 'https://www.flipkart.com'),

-- Gaming & Entertainment
('Epic Games', 'A video game and software development company known for Fortnite and Unreal Engine.', 'Cary, North Carolina', 'https://www.epicgames.com'),
('Unity', 'A video game software development company that provides a game engine for creating video games.', 'San Francisco, California', 'https://www.unity.com'),
('Roblox', 'An online game platform and game creation system that allows users to program games and play games created by other users.', 'San Mateo, California', 'https://www.roblox.com'),
('Discord', 'A VoIP, instant messaging and digital distribution platform designed for creating communities.', 'San Francisco, California', 'https://www.discord.com'),
('Twitch', 'A video live streaming service that focuses on video game live streaming.', 'San Francisco, California', 'https://www.twitch.tv'),
('Niantic', 'A software development company that develops augmented reality mobile games.', 'San Francisco, California', 'https://www.nianticlabs.com'),
('Riot Games', 'A video game developer and publisher known for League of Legends.', 'Los Angeles, California', 'https://www.riotgames.com'),
('Valve', 'A video game developer and digital distribution company known for Steam.', 'Bellevue, Washington', 'https://www.valvesoftware.com'),
('Electronic Arts', 'A video game company that develops and publishes games.', 'Redwood City, California', 'https://www.ea.com'),
('Activision Blizzard', 'A video game holding company that develops and publishes video games.', 'Santa Monica, California', 'https://www.activisionblizzard.com'),

-- HealthTech
('Teladoc', 'A telemedicine and virtual healthcare company that provides remote medical care.', 'Purchase, New York', 'https://www.teladoc.com'),
('Amwell', 'A telehealth company that provides online doctor visits.', 'Boston, Massachusetts', 'https://www.amwell.com'),
('23andMe', 'A personal genomics and biotechnology company that provides genetic testing and analysis.', 'South San Francisco, California', 'https://www.23andme.com'),
('Flatiron Health', 'A healthcare technology and services company focused on accelerating cancer research and improving patient care.', 'New York, New York', 'https://www.flatiron.com'),
('Tempus', 'A technology company that uses artificial intelligence to collect and analyze molecular and clinical data.', 'Chicago, Illinois', 'https://www.tempus.com'),
('Oscar Health', 'A health insurance company that uses technology to make health insurance simple.', 'New York, New York', 'https://www.hioscar.com'),
('GoodRx', 'A healthcare technology company that provides prescription drug pricing information.', 'Santa Monica, California', 'https://www.goodrx.com'),
('Ro', 'A direct-to-patient healthcare company that provides digital health services.', 'New York, New York', 'https://www.ro.co'),
('Hinge Health', 'A digital clinic for joint and muscle pain that combines wearable sensors, exercise therapy, and health coaching.', 'San Francisco, California', 'https://www.hingehealth.com'),
('Omada Health', 'A digital care program that empowers people to achieve their health goals through sustainable lifestyle change.', 'San Francisco, California', 'https://www.omadahealth.com'),

-- EdTech
('Coursera', 'An online learning platform that offers courses, specializations, and degrees.', 'Mountain View, California', 'https://www.coursera.org'),
('Udemy', 'An online learning platform that offers courses on various topics.', 'San Francisco, California', 'https://www.udemy.com'),
('Duolingo', 'A language-learning platform that offers free language education.', 'Pittsburgh, Pennsylvania', 'https://www.duolingo.com'),
('Khan Academy', 'A non-profit educational organization that provides free online courses, lessons, and practice.', 'Mountain View, California', 'https://www.khanacademy.org'),
('Chegg', 'An American education technology company that provides digital and physical textbook rentals, online tutoring, and other student services.', 'Santa Clara, California', 'https://www.chegg.com'),
('Quizlet', 'An online study application that allows students to study information via learning tools and games.', 'San Francisco, California', 'https://www.quizlet.com'),
('Outschool', 'An online marketplace that offers live online classes for kids.', 'San Francisco, California', 'https://www.outschool.com'),
('MasterClass', 'An online education platform that offers classes taught by experts in various fields.', 'San Francisco, California', 'https://www.masterclass.com'),
('Skillshare', 'An online learning community for creators that offers thousands of classes.', 'New York, New York', 'https://www.skillshare.com'),
('Codecademy', 'An online interactive platform that offers free coding classes in 12 different programming languages.', 'New York, New York', 'https://www.codecademy.com');