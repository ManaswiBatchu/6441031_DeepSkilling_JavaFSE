import React from 'react';

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null,
    };
  }

  componentDidMount() {
    this.loadPosts();
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => this.setState({ posts: data }))
      .catch(error => this.setState({ error }));
  }

  render() {
    const { posts, error } = this.state;

    if (error) {
      return <p style={{ textAlign: 'center', color: 'red' }}>Error loading posts: {error.message}</p>;
    }

    return (
      <div style={{ padding: '20px', maxWidth: '900px', margin: 'auto' }}>
        <h1 style={{ textAlign: 'center', marginBottom: '30px', fontFamily: 'Arial' }}>Posts</h1>
        
        {posts.map(post => (
          <div key={post.id} style={{
            border: '1px solid black',
            marginBottom: '20px',
            padding: '20px',
            borderRadius: '5px',
            textAlign: 'center',
            fontFamily: 'sans-serif',
          }}>
            <h2 style={{ fontSize: '20px', fontWeight: 'bold', marginBottom: '10px' }}>{post.title}</h2>
            <p style={{ fontSize: '16px', margin: 0 }}>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
