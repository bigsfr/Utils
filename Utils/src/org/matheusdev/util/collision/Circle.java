/*
 * Copyright (c) 2012 matheusdev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.matheusdev.util.collision;

import org.matheusdev.util.vecmath.Vec2;

/**
 * @author matheusdev
 *
 */
public class Circle implements Easifyable, Projectable {

	protected Vec2 center;
	protected float radius;
	// For pooling:
	protected Vec2 vertex0 = new Vec2();
	protected Vec2 vertex1 = new Vec2();

	public Circle(float x, float y, float radius) {
		this.center = new Vec2(x, y);
		this.radius = radius;
	}

	public Circle(Vec2 center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle setCenter(Vec2 center) {
		this.center = center;
		return this;
	}

	public Circle setCenter(float x, float y) {
		this.center.set(x, y);
		return this;
	}

	public Circle setRadius(float radius) {
		this.radius = radius;
		return this;
	}

	public Circle set(float x, float y, float radius) {
		this.center.set(x, y);
		this.radius = radius;
		return this;
	}

	public Circle set(Vec2 center, float radius) {
		this.center = center;
		this.radius = radius;
		return this;
	}

	public Vec2 getCenter() {
		return center;
	}

	public float getRadius() {
		return radius;
	}

	public float getSqRadius() {
		return radius * radius;
	}

	/* (non-Javadoc)
	 * @see org.matheusdev.util.collision.Easifyable#getAABB()
	 */
	@Override
	public Rect getAABB() {
		return new Rect(center.x-radius, center.y-radius, radius*2, radius*2);
	}

	/* (non-Javadoc)
	 * @see org.matheusdev.util.collision.Easifyable#getBounds()
	 */
	@Override
	public Circle getBounds() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.matheusdev.util.collision.Projectable#project(org.matheusdev.util.vecmath.Vec2)
	 */
	@Override
	public Vec2 project(Vec2 axis, Vec2 dest) {
		if (dest == null) dest = new Vec2();
		vertex0.set(axis.x, axis.y).scale(radius).translate(center.x, center.y);
		vertex1.set(-axis.x, -axis.y).scale(radius).translate(center.x, center.y);
		float p0 = Vec2.dot(vertex0, axis);
		float p1 = Vec2.dot(vertex1, axis);
		return dest.set(Math.min(p0, p1), Math.max(p0, p1));
	}

}
