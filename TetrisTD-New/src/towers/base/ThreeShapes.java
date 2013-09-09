package towers.base;

import com.badlogic.gdx.math.Polygon;

public enum ThreeShapes {	
	STRAIGHT(
			//Shape
			new float[]{
				0, 0,
				0, 1,
				0, 2
			},
			//ShapeBody
			new Polygon(new float[]{
				0, 0,
				32, 0,
				32, 96,
				0, 96
			}),
			//RangeBody
			new Polygon(new float[]{
				-32, -32,
				64, 0,
				64, 128,
				-32, 128
			})
			),
	BENT(
			//Shape
			new float[] {
				0, 0,
				1, 0,
				0, 1
			},
			//ShapeBody
			new Polygon(new float[] {
				0, 0, 
				64, 0,
				64, 64,
				32, 64,
				32, 32,
				0, 32,
			}),
			//RangeBody
			new Polygon(new float[] {
				-32, -32,
				96, -32,
				96, 96,
				0, 96,
				0, 64,
				-32, 64,
			})
			);
	private ThreeShapes(float[] shape, Polygon shapeBody, Polygon rangeBody) {
		this.shape = shape;
		this.shapeBody = shapeBody;
		this.rangeBody = rangeBody;
	}
	
	float[] shape;
	Polygon shapeBody;
	Polygon rangeBody;
	
	public float[] getShape() {
		return this.shape;
	}
	public Polygon getShapeBody() {
		return this.shapeBody;
	}
	public Polygon getRangeBody() {
		return this.rangeBody;
	}
	
	public static ThreeShapes getRandomShape() {
		return ThreeShapes.values()[(int)(Math.random()*ThreeShapes.values().length)];
	}
}
