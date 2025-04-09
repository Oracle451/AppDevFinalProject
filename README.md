# Movie Hunter

## Overview
Movie Hunter is an Android application designed to help movie enthusiasts search for films and manage personalized movie lists. Leveraging The Movie Database (TMDb) API, the app provides a seamless experience for discovering movie details and organizing them into custom lists. Whether you're exploring new releases or curating your favorites, Movie Hunter offers an intuitive interface for movie lovers.

## Features and Functionality
Movie Hunter offers the following key features:

- **Movie Search**: Search for movies using keywords, with results powered by The Movie Database API. Results are displayed as a scrollable list on the main screen.
- **Detailed Movie View**: Tap on any movie from the search results to access a detailed view, including:
  - Movie poster
  - Title
  - Release date
  - Summary
  - Rating
  - Option to add the movie to a custom list
- **Custom Movie Lists**: Create, view, and manage personalized lists of movies. From the main screen, users can:
  - Access a "View Lists" menu to see all existing lists
  - Create new lists
  - Dive into individual lists to view their contents
- **List Management**: Within each list, users can:
  - See the list name and all included movies
  - Rename the list
  - Delete the list
- **Offline Access**: Movies saved in custom lists can be viewed offline, while searching requires an internet connection.

## Instructions and Requirements

### System Requirements
- **Operating System**: Android Version 7.0 (Nougat) or Higher Recommended
- **Internet Access**: Required for searching movies via The Movie Database API. Offline mode is supported for viewing saved lists.
- **Storage**: Minimal local storage is needed to cache list data and movie details for offline use.

### Usage
1. **Start Screen**: Upon launching, choose between searching for movies or viewing your lists.
2. **Search Movies**: Enter a movie title or keyword in the search bar to retrieve a list of results.
3. **Explore Details**: Tap a movie to see its full details and add it to a list if desired.
4. **Manage Lists**: Navigate to "View Lists" to create a new list or select an existing one. Rename or delete lists as needed.

## Dependencies
- **The Movie Database (TMDb) API**: Used for real-time movie data retrieval. An API key is embedded in the app for seamless operation.
- **Android Libraries**: Standard Android SDK components for UI, networking, and local storage.

